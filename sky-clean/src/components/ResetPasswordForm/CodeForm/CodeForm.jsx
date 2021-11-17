//general
import React from 'react'
//ui
import FormInput from "../../../UI/FormInput/FormInput";
import Spinner from "../../../UI/Spinner/Spinner";
//styles
import './CodeForm.scss'

function CodeForm({values, errors, formik, handleChange, loading}) {
  return (
    !loading ? (
      <form className="codeForm" onSubmit={formik.handleSubmit}>
        <div className="codeForm__header">
          <h3>Code verification</h3>
          <p>
            Enter the received code in the field and you will be sent a new password to your mail.
          </p>
        </div>
    
        <div className="codeForm__body">
          <div className="codeForm__inputs">
            <div className="codeForm__field">
              <p>Code</p>
              <div className="codeForm__input">
                <FormInput name="code" value={values.code} onChange={handleChange} errors={errors.code}/>
              </div>
            </div>
          </div>
        </div>
    
        <div className="codeForm__footer">
          <input type="submit" value="Submit"/>
        </div>
      </form>
    ) : (
      <div className="spinnerContainer">
        <Spinner/>
      </div>
    )
  )
}

export default CodeForm