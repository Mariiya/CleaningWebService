//general
import React from 'react'
import * as Yup from "yup";
import {useFormik} from "formik";
import {notify} from "../../../helpers/notify/notify";
//ui
import Spinner from "../../../UI/Spinner/Spinner";
//components
import CustomServiceForm from "./CustomServiceForm";

function CustomServiceFormEditor({handleShowFormCloseClick}) {
  const [loader, setLoader] = React.useState(false)
  
  const initialValues = {
    name: '',
  }
  
  const validationSchema = Yup.object().shape({
    name: Yup.string()
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
      console.log(values)
      notify('Success', 'You successfully added custom service!')
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