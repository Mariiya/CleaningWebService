//general
import React from 'react'
import {useFormik} from "formik";
import * as Yup from 'yup'
import {useDispatch} from "react-redux";
//api
import {updateOrder} from "../../../api/orders.api";
//helpers
import {notify} from "../../../helpers/notify/notify";
//components
import Order from "./Order";
import {updateOrderRecord} from "../../../store/orders/actions";

const OrderFormEditor = ({order}) => {
    const dispatch = useDispatch()

    const [editableOrderData, setEditableOrderData] = React.useState(false)

    const initialValues = {
        title: order.title,
        description: order.description,
        address: order.address,
        price: order.price,
    }

    const validationSchema = Yup.object().shape({
        title: Yup.string()
            .min(2, 'To short!')
            .max(30, 'Too long!')
            .required('Required'),
        description: Yup.string()
            .min(5, 'To short!')
            .max(255, 'Too long!')
            .required('Required'),
        address: Yup.string()
            .min(10, 'To short!')
            .max(255, 'Too long!')
            .required('Required'),
        price: Yup.string()
            .matches(/^[0-9]+$/, "Must be only digits")
            .required('Required'),
    })

    const form = useFormik({
        initialValues,
        validationSchema,
        validateOnChange: false,
        onSubmit: (values) => {
            const data = {
                ...order,
                ...values,
            }
            updateOrder(data).then(() => {
                dispatch(updateOrderRecord(order.id, data))
                setEditableOrderData(false)
                notify('Success', 'You are successfully updated you order !')
            })
        }
    })

    const handleChange = (event) => {
        form.handleChange(event)
    }

    const handleEditOrderData = () => {
        setEditableOrderData(true)
    }

    return (
        <Order
          order={order}
          values={form.values}
          errors={form.errors}
          form={form}
          handleChange={handleChange}
          editableOrderData={editableOrderData}
          handleEditOrderData={handleEditOrderData}/>
    )
}

export default OrderFormEditor