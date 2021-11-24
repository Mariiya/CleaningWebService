//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
import {useSelector} from "react-redux";
//components
import UserDataForm from "./UserDataForm";


function UserDataFormEditor() {
  const userInfo = useSelector((state) => state.user.userInfo)
  
  const initialValues = {
    name: userInfo.firstName,
    surName: userInfo.lastName,
    email: userInfo.email,
    phoneNumber: userInfo.phoneNumber,
  }
  
  const phoneRegExp = /^(\+?\d{0,4})?\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{4}\)?)?$/
  
  const validationSchema = Yup.object().shape({
    name: Yup.string()
      .min(2, 'To short!')
      .max(30, 'Too long!')
      .required('Required'),
    surName: Yup.string()
      .min(2, 'To short!')
      .max(30, 'Too long!')
      .required('Required'),
    email: Yup.string().email('Invalid email').required('Required'),
    phoneNumber: Yup.string()
      .matches(phoneRegExp, "Must be only digits")
      .min(13, 'Too Short!')
      .max(15, 'Too Long!')
      .required('Required'),
  })
  
  const form = useFormik({
    initialValues,
    validationSchema,
    validateOnChange: false,
    onSubmit: (values) => {
      console.log(values)
    }
  })
  
  const handleChange = (event) => {
    form.handleChange(event)
  }
  
  return (
    <UserDataForm
      values={form.values}
      errors={form.errors}
      form={form}
      handleChange={handleChange}/>
  )
}

export default UserDataFormEditor