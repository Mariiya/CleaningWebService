//general
import React from 'react'
import * as Yup from 'yup'
import {useFormik} from "formik";
import {useDispatch} from "react-redux";
import {sha256} from "js-sha256";
//api
import {addNewUser, getAccessToken} from "../../api/auth.api";
//redux
import {getToken, getUserInfo} from "../../store/user/actions";
//helpers
import {notify} from "../../helpers/notify/notify";
//components
import SignUpForm from "./SignUpForm";


const SignUpFormEditor = () => {
  const dispatch = useDispatch()
  
  const [userRole, setUserRole] = React.useState(false);
  const [vendorType, setVendorType] = React.useState(false);
  
  const initialValues = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    email: '',
    password: '',
    repeatPassword: '',
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
      .max(15, 'Too Long!')
      .required('Required'),
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
    initialValues,
    validationSchema,
    validateOnChange: false,
    onSubmit: (values) => {
      const {firstName, lastName, password, email, phoneNumber, repeatPassword} = values
      if (password === repeatPassword) {
        let data = null
        if (!userRole) {
          data = {
            firstName,
            lastName,
            phoneNumber,
            email,
            password: sha256(password),
            role: 'ROLE_CLIENT',
          }
        } else {
          data = {
            firstName,
            lastName,
            phoneNumber,
            email,
            password: sha256(password),
            individual: vendorType,
            role: 'ROLE_SERVICE_PROVIDER',
          }
        }
        addNewUser(data, !userRole ? 'consumer' : 'vendor').then((response) => {
          if (response === true) {
            const userData = {
              email: data.email,
              password: data.password,
            }
            getAccessToken(userData, '/api/auth/signin').then((response) => {
              dispatch(getToken(response.token))
              dispatch(getUserInfo(response.user))
              notify('Success', 'You are successfully registered !')
            })
          } else {
            const errors = response.errors
                errors.forEach((error) =>
                  notify.error(response.message, error))
          }
          
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
      userRole={userRole}
      setUserRole={setUserRole}
      vendorType={vendorType}
      setVendorType={setVendorType}/>
  )
}

export default SignUpFormEditor