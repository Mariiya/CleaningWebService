//general
import React from 'react'
//ui
import FormInput from "../../../UI/FormInput/FormInput";
//styles
import './UserPasswordForm.scss'


function UserPasswordForm({values, errors, form, handleChange}) {
  return (
    <form className="userPasswordForm" onSubmit={form.handleSubmit}>
      <div className="userPasswordForm__body">
        <div className="userPasswordForm__field">
          <h3 className="userPasswordForm__fieldName">Change password</h3>
          
          <div className="userPasswordForm__input">
            <FormInput
              name="password"
              type="password"
              value={values.password}
              errors={errors.password}
              onChange={handleChange}/>
          </div>
  
          <div className="userPasswordForm__input">
            <FormInput
              name="repeatPassword"
              type="password"
              value={values.repeatPassword}
              errors={errors.repeatPassword}
              onChange={handleChange}/>
          </div>
        </div>
      </div>
      
      <div className="userPasswordForm__footer">
        <button
          type="submit"
          className="userPasswordForm__submitBtn">
          Save
        </button>
      </div>
    </form>
  )
}

export default UserPasswordForm