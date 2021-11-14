//general
import React from 'react'
import * as Yup from 'yup'
import {useFormik} from "formik";
import {useHistory} from "react-router";
//api
import {sendCodeForResetPassword} from "../../../api/reset.api";
//helpers
import {notify} from "../../../helpers/notify/notify";
//components
import CodeForm from "./CodeForm";


const CodeFormEditor = ({setCodeFormVisible, code, setCode, email, setEmail}) => {
  const history = useHistory();
  
  const initialValues = {
    code: '',
  }
  
  const validationSchema = Yup.object().shape({
    code: Yup.string()
      .required('Required'),
  })
  
  const form = useFormik({
    initialValues,
    validationSchema,
    validateOnChange: false,
    onSubmit: (values => {
        if (values.code === code) {
          sendCodeForResetPassword(email).then(() => {
            setCodeFormVisible(false)
            history.push('/sign-in')
            setEmail(null)
            setCode(null)
            notify('Success', 'Your password has been successfully updated')
          })
        } else {
          notify.error('Rejected', 'You made mistakes when entering your code')
        }
      form.resetForm()
    })
  })
  
  const handleChange = (event) => {
    form.handleChange(event)
  }
  
  return (
    <CodeForm
      values={form.values}
      errors={form.errors}
      formik={form}
      handleChange={handleChange}/>
  )
}

export default CodeFormEditor