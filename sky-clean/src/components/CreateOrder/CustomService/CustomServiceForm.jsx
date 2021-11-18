//general
import React from 'react'
//ui
import FormInput from "../../../UI/FormInput/FormInput";
//styles
import './CustomServiceForm.scss'

function CustomServiceForm({values, errors, form, handleChange, handleShowFormCloseClick}) {
  return (
    <form className="customServiceForm" onSubmit={form.handleSubmit}>
      <div className="customServiceForm__header">
        <h3 className="customServiceForm__title">
          Service you are wishing for is not in the list? You can write your own version below:
        </h3>
      </div>
  
      <div className="customServiceForm__body">
        <div className="customServiceForm__input">
          <FormInput placeholder="Enter your service name" name="name" value={values.name} onChange={handleChange} errors={errors.name}/>
        </div>
      </div>
  
      <div className="customServiceForm__footer">
        <button className="customServiceForm__btn" onClick={handleShowFormCloseClick}>
          Back
        </button>
        
        <button type="submit" className="customServiceForm__btn">
          Save
        </button>
      </div>
    </form>
  )
}

export default CustomServiceForm