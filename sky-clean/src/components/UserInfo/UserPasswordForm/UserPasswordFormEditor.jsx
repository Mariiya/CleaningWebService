//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
import {notify} from "../../../helpers/notify/notify";
import {useDispatch, useSelector} from "react-redux";
//redux
import {clearToken} from "../../../store/user/actions";
//api
import {changePassword} from "../../../api/account.api";
//components
import UserPasswordForm from "./UserPasswordForm";
import {sha256} from "js-sha256";

function UserPasswordFormEditor() {
  const dispatch = useDispatch()
  const userInfo = useSelector((state) => state.user.userInfo)

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
      if (values.password === values.repeatPassword) {
        const data = {
          ...userInfo,
          password: sha256(values.password),
        }

        changePassword(data).then((response) => {
          if (response === true) {
            notify('Success', 'You successfully changed your password')
            dispatch(clearToken())
          }
        })
      } else {
        notify.error('Error', 'Your passwords do not match')
      }
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