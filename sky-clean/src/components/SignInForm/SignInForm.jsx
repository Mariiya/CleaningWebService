//general
import React from 'react';
import {NavLink} from "react-router-dom";
//ui
import FormInput from "../../UI/FormInput/FormInput";
//styles
import './SignInForm.scss';
import Spinner from "../../UI/Spinner/Spinner";


function SignInForm({values, errors, formik, handleChange, loading}) {
  
  return (
    !loading ? (
    <form className="signInForm" onSubmit={formik.handleSubmit}>
      <div className="signInForm__body">
        <div className="signInForm__inputs">
          <div className="signInForm__field">
            <p>E-mail</p>
            <div className="signInForm__input">
              <FormInput name="email" value={values.email} onChange={handleChange} errors={errors.email}/>
            </div>
          </div>

          <div className="signInForm__field">
            <p>Password</p>
            <div className="signInForm__input">
              <FormInput name="password" type="password" value={values.password} onChange={handleChange} errors={errors.password}/>
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
  ) : (
    <div className="spinnerContainer">
      <Spinner/>
    </div>
  )
  );
};

export default SignInForm;