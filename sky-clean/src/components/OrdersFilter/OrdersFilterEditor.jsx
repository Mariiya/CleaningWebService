//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
import {useDispatch} from "react-redux";
//components
import OrdersFilter from "./OrdersFilter";
//redux
import {setFilters} from "../../store/orders/actions";

const OrdersFilterEditor = () => {
    const dispatch = useDispatch()
    const [selectedService, setSelectedService] = React.useState(0)

    const initialValues = {
        title: '',
        minPrice: '',
        maxPrice: '',
        service: selectedService
    }

    const validationSchema = Yup.object().shape({
        minPrice: Yup.string().matches(/^\d*[0-9]\d*$/, "Must be only digits and positive"),
        maxPrice: Yup.string().matches(/^\d*[0-9]\d*$/, "Must be only digits and positive")
    })

    const form = useFormik({
        initialValues,
        validationSchema,
        validateOnChange: false,
        onSubmit: (values) => {
            const filters = {
                ...values,
                maxPrice: Number(values.maxPrice),
                minPrice: Number(values.minPrice),
                service: selectedService
            }
            if (values.maxPrice === '') {
                filters.maxPrice = 0
            }
            if (values.minPrice === '') {
                filters.minPrice = 0
            }
            dispatch(setFilters(filters))
        }
    })

    const handleChange = (event) => {
        form.handleChange(event)
    }

    return (
        <OrdersFilter
          values={form.values}
          errors={form.errors}
          form={form}
          handleChange={handleChange}
          setSelectedService={setSelectedService}/>
    )
}

export default OrdersFilterEditor