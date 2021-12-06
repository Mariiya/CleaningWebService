//general
import React from 'react'
import {useFormik} from "formik";
import * as Yup from 'yup'
import {sha256} from "js-sha256";
import {useHistory} from "react-router";
//redux
import {useDispatch} from "react-redux";
import {getToken, getUserInfo} from "../../store/user/actions";
//api
import {getAccessToken} from "../../api/auth.api";
//helpers
import {notify} from "../../helpers/notify/notify";
//components
import SignInForm from "./SignInForm";

const SingInFormEditor = () => {
  const dispatch = useDispatch()
  const history = useHistory()
  
  const [loading, setLoading] = React.useState(false)
  
  const initialValues = {
    email: '',
    password: '',
  }
  
  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email('Invalid email')
      .required('Required'),
    password: Yup.string()
      .min(7, 'To short!')
      .max(250, 'Too long!')
      .required('Required'),
  })
  
  const form = useFormik({
    initialValues,
    validationSchema,
    validateOnChange: false,
    onSubmit: (values) => {
      const {email, password} = values
      const data = {
        email,
        password: sha256(password)
      }
      setLoading(true)
      getAccessToken(data).then((response) => {
          if (!response.errors) {
            const userData = response.user
            delete userData.password
            dispatch(getToken(response.token))
            dispatch(getUserInfo(response.user))
            history.push('/account')
            notify('Success', 'You are successfully authorized !')
          } else {
            setLoading(false)
            const errors = response.errors
            errors.forEach((error) =>
              notify.error(response.message, error))
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
      handleChange={handleChange}
      loading={loading}/>
  )
}

export default SingInFormEditor