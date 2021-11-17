//general
import React from 'react';
//ui
import FormInput from "../../UI/FormInput/FormInput";
import Spinner from "../../UI/Spinner/Spinner";
//styles
import './ResetPasswordForm.scss';

function ResetPasswordForm({values, errors, formik, handleChange, loading}) {
  return (
    !loading ? (
      <form className="resetPasswordForm" onSubmit={formik.handleSubmit}>
        <div className="resetPasswordForm__header">
          <h3>Forgot your password?</h3>
          <p>
            If you continue, SkyClean will send a message to the email address in your profile.
          </p>
        </div>
    
        <div className="resetPasswordForm__body">
          <div className="resetPasswordForm__inputs">
            <div className="resetPasswordForm__field">
              <p>E-mail</p>
              <div className="resetPasswordForm__input">
                <FormInput name="email" value={values.email} onChange={handleChange} errors={errors.email}/>
              </div>
            </div>
          </div>
        </div>
    
        <div className="resetPasswordForm__footer">
          <input type="submit" value="Submit"/>
        </div>
      </form>
    ) : (
      <div className="spinnerContainer">
        <Spinner/>
      </div>
    )
  );
};

export default ResetPasswordForm;