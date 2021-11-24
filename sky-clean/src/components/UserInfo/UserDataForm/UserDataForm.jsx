//general
import React from 'react'
//ui
import FormInput from "../../../UI/FormInput/FormInput";
//styles
import './UserDataForm.scss'


function UserDataForm({values, errors, form, handleChange}) {
  return (
    <form className="userDataForm" onSubmit={form.handleSubmit}>
      <div className="userDataForm__body">
        <div className="userDataForm__field">
          <h3 className="userDataForm__fieldName">Name</h3>
          
          <div className="userDataForm__input">
            <FormInput
              name="name"
              value={values.name}
              errors={errors.name}
              onChange={handleChange}/>
          </div>
        </div>
  
        <div className="userDataForm__field">
          <h3 className="userDataForm__fieldName">Surname</h3>
    
          <div className="userDataForm__input">
            <FormInput
              name="surName"
              value={values.surName}
              errors={errors.surName}
              onChange={handleChange}/>
          </div>
        </div>
  
        <div className="userDataForm__field">
          <h3 className="userDataForm__fieldName">E-mail</h3>
    
          <div className="userDataForm__input">
            <FormInput
              name="email"
              value={values.email}
              errors={errors.email}
              onChange={handleChange}/>
          </div>
        </div>
  
        <div className="userDataForm__field">
          <h3 className="userDataForm__fieldName">Phone number</h3>
    
          <div className="userDataForm__input">
            <FormInput
              name="phoneNumber"
              value={values.phoneNumber}
              errors={errors.phoneNumber}
              onChange={handleChange}/>
          </div>
        </div>
      </div>
      
      <div className="userDataForm__footer">
        <button
          type="submit"
          className="userDataForm__submitBtn">
          Save
        </button>
      </div>
    </form>
  )
}

export default UserDataForm