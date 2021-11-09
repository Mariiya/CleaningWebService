//general
import React from 'react'
import {useFormik} from "formik";
import * as Yup from 'yup'
import {sha256} from "js-sha256";
import {useHistory} from "react-router";
//api
import {getAccessToken} from "../../api/auth.api";
//components
import SignInForm from "./SignInForm";

const SingInFormEditor = () => {
  const history = useHistory()
  
  const initialValues = {
    email: '',
    password: '',
  }
  
  const validationSchema = Yup.object().shape({
    email: Yup.string().email('Invalid email').required('Required'),
    password: Yup.string()
      .min(7, 'To short!')
      .max(250, 'Too long!')
      .required('Required'),
  })
  
  const form = useFormik({
    initialValues: initialValues,
    validationSchema: validationSchema,
    validateOnChange: false,
    onSubmit: (values) => {
      const {email, password} = values
      const data = {
        email,
        password: sha256(password)
      }
      getAccessToken(data, '/api/auth/signin').then((response) => {
        if (response.token) {
          localStorage.setItem('accessToken', response.token)
          history.push('/')
        }
      })
      form.resetForm()
    }
  })
  
  const handleChange = (event) => {
    form.handleChange(event)
  }
  
  return (
    <SignInForm
      values={form.values}
      errors={form.errors}
      formik={form}
      handleChange={handleChange}/>
  )
}

export default SingInFormEditor