//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
import {notify} from "../../helpers/notify/notify";
import {useDispatch, useSelector} from "react-redux";
//redux
import {clearCustomServices, setServices} from "../../store/services/actions";
//api
import {createNewOrder, getServices} from "../../api/createOrder.api";
//ui
import Spinner from "../../UI/Spinner/Spinner";
//components
import CreateOrderForm from "./CreateOrderForm";

function CreateOrderFormEditor() {
  const dispatch = useDispatch()
  const services = useSelector((state) => state.services.services)
  const servicesForOrder = services?.filter((service) => service.state === true)
  const user = localStorage.getItem('user_info')
  
  const [loader, setLoader] = React.useState(false)
  
  React.useEffect(() => {
    getServices().then((response) => {
      dispatch(setServices(response))
    })
  }, [dispatch])
  
  const initialValues = {
    name: '',
    description: '',
    address: '',
    price: '',
  }
  
  const validationSchema = Yup.object().shape({
    name: Yup.string()
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
    price: Yup.number()
      .positive('Must be only positive')
      .required('Required'),
  })
  
  const form = useFormik({
    initialValues,
    validationSchema,
    validateOnChange: false,
    onSubmit: (values) => {
      if (servicesForOrder.length) {
        servicesForOrder.forEach((service) => {
          delete service.state
        })
        const today = new Date();
        const dd = String(today.getDate()).padStart(2, '0');
        const mm = String(today.getMonth() + 1).padStart(2, '0');
        const yyyy = today.getFullYear();
        const startDate = yyyy + '-' + mm + '-' + dd
        const data = {
          id: 2223123,
          consumer: JSON.parse(user),
          title: values.name,
          description: values.description,
          status: 'STATUS_OPEN',
          startDate: startDate,
          price: values.price,
          services: servicesForOrder,
          address: values.address,
          endDate: startDate,
        }
        
        setLoader(true)
        
        createNewOrder(data).then((response) => {
          if (response) {
            dispatch(clearCustomServices())
            form.resetForm()
            notify('Success', 'You are successfully created order!')
          } else {
            notify.error('Error', 'Something went wrong, please try later')
          }
        })
        .finally(() => {
          setLoader(false)
        })
      } else {
        notify.error('Error', 'You should select service for your order')
      }
    }
  })
  
  const handleChange = (event) => {
    form.handleChange(event)
  }
  
  return (
    !loader ? (
      <CreateOrderForm
        values={form.values}
        errors={form.errors}
        form={form}
        handleChange={handleChange}/>
    ) : (
      <div className="spinnerContainer">
        <Spinner/>
      </div>
    )
  )
}

export default CreateOrderFormEditor