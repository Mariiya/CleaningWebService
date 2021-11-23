//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
//components
import UserPasswordForm from "./UserPasswordForm";

function UserPasswordFormEditor() {
  const initialValues = {
    password: '',
    repeatPassword: '',
  }
  
  const validationSchema = Yup.object().shape({
    password: Yup.string()
      .min(7, 'To short!')
      .max(250, 'Too long!')
      .required('Required'),
    repeatPassword: Yup.string()
      .min(7, 'To short!')
      .max(250, 'Too long!')
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
    <UserPasswordForm
      values={form.values}
      errors={form.errors}
      form={form}
      handleChange={handleChange}/>
  )
}

export default UserPasswordFormEditor