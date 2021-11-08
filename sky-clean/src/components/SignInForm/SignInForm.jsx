//general
import React from 'react';
import {NavLink} from "react-router-dom";
//ui
import FormInput from "../../UI/FormInput/FormInput";
//styles
import './SignInForm.scss';


function SignInForm() {
  const [password, setPassword] = React.useState('')
  
  const onPasswordHandleChange = (str) => {
    setPassword(str)
  }
  
  
  
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
              <FormInput type="password" value={password} onChange={onPasswordHandleChange}/>
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