//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
import {notify} from "../../../helpers/notify/notify";
import {useDispatch} from "react-redux";
//api
import {createCustomService} from "../../../api/createOrder.api";
//redux
import {addCustomService} from "../../../store/services/actions";
//ui
import Spinner from "../../../UI/Spinner/Spinner";
//components
import CustomServiceForm from "./CustomServiceForm";

function CustomServiceFormEditor({handleShowFormCloseClick}) {
  const dispatch = useDispatch()
  
  const [loader, setLoader] = React.useState(false)
  
  const initialValues = {
    name: '',
    description: '',
  }
  
  const validationSchema = Yup.object().shape({
    name: Yup.string()
      .min(2, 'To short!')
      .max(20, 'Too long!')
      .required('Required'),
    description: Yup.string()
      .min(5, 'To short!')
      .max(250, 'Too long!')
      .required('Required'),
  })
  
  const form = useFormik({
    initialValues,
    validationSchema,
    validateOnChange: false,
    onSubmit: (values) => {
      setLoader(true)
      createCustomService(values).then((response) => {
        if (response) {
          dispatch(addCustomService(response))
          handleShowFormCloseClick()
          notify('Success', 'You successfully added custom service!')
        } else {
          setLoader(false)
          notify.error('Error', 'Something went wrong, please try later')
        }
      })
    }
  })
  
  const handleChange = (event) => {
    form.handleChange(event)
  }
  
  return (
    !loader ? (
      <CustomServiceForm
        values={form.values}
        errors={form.errors}
        form={form}
        handleChange={handleChange}
        handleShowFormCloseClick={handleShowFormCloseClick}/>
    ) : (
      <div className="spinnerContainerAbsolute">
        <Spinner/>
      </div>
    )
  )
}

export default CustomServiceFormEditor