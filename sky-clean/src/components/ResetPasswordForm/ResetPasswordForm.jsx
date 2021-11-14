//general
import React from 'react';
//styles
import './ResetPasswordForm.scss';
import FormInput from "../../UI/FormInput/FormInput";

function ResetPasswordForm({values, errors, formik, handleChange}) {
  return (
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
  );
};

export default ResetPasswordForm;