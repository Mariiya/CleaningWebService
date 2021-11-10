//general
import React from 'react'
import {useFormik} from "formik";
import * as Yup from 'yup'
//api
import {addNewUser} from "../../api/auth.api";
//components
import SignUpForm from "./SignUpForm";
import {sha256} from "js-sha256";

const SignUpFormEditor = () => {
  const [checkBoxState, setCheckBoxState] = React.useState(false);
  
  const initialValues = {
    firstName: '',
    lastName:'',
    phoneNumber: '',
    email: '',
    password: '',
    repeatPassword: '',
    role: 'ROLE_CLIENT',
  }
  
  const phoneRegExp = /^(\+?\d{0,4})?\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{4}\)?)?$/
  
  const validationSchema = Yup.object().shape({
    firstName: Yup.string()
      .min(2, 'To short!')
      .max(20, 'Too long!')
      .required('Required'),
    lastName: Yup.string()
      .min(2, 'To short!')
      .max(20, 'Too long!')
      .required('Required'),
    phoneNumber: Yup.string()
      .matches(phoneRegExp, "Must be only digits")
      .min(13, 'Too Short!')
      .max(15, 'Too Long!'),
    email: Yup.string().email('Invalid email').required('Required'),
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
    initialValues: initialValues,
    validationSchema: validationSchema,
    validateOnChange: false,
    onSubmit: (values) => {
      const {firstName, lastName, password, role, email, phoneNumber, repeatPassword} = values
      if (password === repeatPassword) {
        const data = {
          firstName,
          lastName,
          phoneNumber,
          email,
          password: sha256(password),
          role,
        }
        addNewUser(data, '/api/auth/signup/consumer')
      }
      form.resetForm()
    }
  })
  
  const handleChange = (event) => {
    form.handleChange(event)
  }
  
  return (
    <SignUpForm
      values={form.values}
      errors={form.errors}
      formik={form}
      handleChange={handleChange}
      checkBoxState={checkBoxState}
      setCheckBoxState={setCheckBoxState}/>
  )
}

export default SignUpFormEditor