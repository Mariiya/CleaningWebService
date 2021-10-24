//general
import React from 'react';
//styles
import './SignInForm.scss';
import FormInput from "../../UI/FormInput/FormInput";
import {NavLink} from "react-router-dom";

function SignInForm() {
  return (
    <form className="signInForm">
      <div className="signInForm__body">
        <div className="signInForm__inputs">
          <div className="signInForm__field">
            <p>E-mail</p>
            <div className="signInForm__input">
              <FormInput type="mail"/>
            </div>
          </div>

          <div className="signInForm__field">
            <p>Password</p>
            <div className="signInForm__input">
              <FormInput type="password"/>
            </div>
          </div>
        </div>
      </div>

      <div className="signInForm__footer">
        <input type="submit" value="Sign in"/>

        <div className="signInForm__notMember">
          <p>Not a Member yet?</p>
          <NavLink to="/sign-up">
            Sign up
          </NavLink>
        </div>

        <div className="signInForm__forgot">
          <p>Forgot your password?</p>
          <NavLink to="/password-reset">
            Reset password
          </NavLink>
        </div>
      </div>
    </form>
  );
};

export default SignInForm;