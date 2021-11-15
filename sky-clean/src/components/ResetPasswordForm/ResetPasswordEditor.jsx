//general
import React from 'react'
import {useFormik} from "formik";
import * as Yup from 'yup'
//api
import {sendMailForResetPassword} from "../../api/reset.api";
//helpers
import {notify} from "../../helpers/notify/notify";
//components
import ResetPasswordForm from "./ResetPasswordForm";
import CodeFormEditor from "./CodeForm/CodeFormEditor";


const ResetPasswordFormEditor = () => {
  const [codeFormVisible, setCodeFormVisible] = React.useState(false)
  const [code, setCode] = React.useState(null)
  const [email, setEmail] = React.useState(null)
  
  const initialValues = {
    email: '',
  }
  
  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email('Invalid email')
      .required('Required'),
  })
  
  const form = useFormik({
    initialValues,
    validationSchema,
    validateOnChange: false,
    onSubmit: (values => {
      sendMailForResetPassword(values.email).then((response) => {
        if (response.code) {
          notify('Success', 'We have sent you an email with a one-time code')
          setCode(response.code)
          setCodeFormVisible(true)
          setEmail(values.email)
        }
      })
      form.resetForm()
    })
  })
  
  const handleChange = (event) => {
    form.handleChange(event)
  }
  
  return (
    <>
      {codeFormVisible ? (
        <CodeFormEditor
          setCodeFormVisible={setCodeFormVisible}
          code={code}
          setCode={setCode}
          email={email}
          setEmail={setEmail}/>
      ) : (
        <ResetPasswordForm
          values={form.values}
          errors={form.errors}
          formik={form}
          handleChange={handleChange}
          codeFormVisible={codeFormVisible}
          setCodeFormVisible={setCodeFormVisible}/>
      )}
    </>
  )
}

export default ResetPasswordFormEditor