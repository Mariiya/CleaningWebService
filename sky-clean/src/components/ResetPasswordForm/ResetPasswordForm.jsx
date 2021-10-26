//general
import React from 'react';
//styles
import './ResetPasswordForm.scss';
import FormInput from "../../UI/FormInput/FormInput";

function ResetPasswordForm() {
  return (
    <form className="resetPasswordForm">
      <div className="resetPasswordForm__header">
        <h3>Forgot your password?</h3>
        <p>
          If you continue, SkyClean will send a message to the E-Mail address in your profile.
          Click the link in the message, and enter a new password on the page that opens.
        </p>
      </div>

      <div className="resetPasswordForm__body">
        <div className="resetPasswordForm__inputs">
          <div className="resetPasswordForm__field">
            <p>E-mail</p>
            <div className="resetPasswordForm__input">
              <FormInput type="mail"/>
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