//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
import {notify} from "../../helpers/notify/notify";
//api
import {getServices} from "../../api/createOrder.api";
//ui
import Spinner from "../../UI/Spinner/Spinner";
//components
import CreateOrderForm from "./CreateOrderForm";


function CreateOrderFormEditor() {
  const [loader, setLoader] = React.useState(false)
  const [services, setServices] = React.useState([])
  const [servicesForOrder, setServicesForOrder] = React.useState([])
  
  React.useEffect(() => {
    getServices().then((response) => {
      setServices(response)
    })
  }, [])
  
  const initialValues = {
    name: '',
    description: '',
    address: '',
    price: '',
  }
  
  const validationSchema = Yup.object().shape({
    name: Yup.string()
      .min(5, 'To short!')
      .max(250, 'Too long!')
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
      if (servicesForOrder.length) {
        console.log(values)
        setLoader(true)
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
        handleChange={handleChange}
        services={services}/>
    ) : (
      <div className="spinnerContainer">
        <Spinner/>
      </div>
    )
  )
}

export default CreateOrderFormEditor