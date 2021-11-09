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
    id: 42,
    firstName: 'daniil ',
    lastName:'bavykin',
    phoneNumber: '+380996304341',
    email: 'bavykin.daniil.inc@gmail.com',
    password: 'qwerty123',
    repeatPassword: 'qwerty123',
    role: 'ROLE_CLIENT',
  }
  
  const phoneRegExp = /^((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$/
  
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
      .matches(phoneRegExp, 'Phone number is not valid')
      .min(11, 'Too Short!')
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
    validateOnChange: false,
    onSubmit: (values) => {
      const {firstName, lastName, id, password, role, email, phoneNumber, repeatPassword} = values
      if (password === repeatPassword) {
        const data = {
          firstName,
          lastName,
          id,
          phoneNumber,
          email,
          password: sha256(password),
          role,
        }
        addNewUser(data, '/api/auth/signup/consumer').then((response) => {
          console.log(response)
        })
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