//general
import React from 'react';
//styles
import './SignUpForm.scss';
import FormInput from "../../UI/FormInput/FormInput";
import CheckBox from "./CheckBox.jsx/CheckBox";
import {NavLink} from "react-router-dom";

function SignUpForm() {
  const [checkBoxState, setCheckBoxState] = React.useState(false);

  const handleCheckBoxChange = () => {
    setCheckBoxState(!checkBoxState)
  }

  return (
    <form className="signUpForm">
      <div className="signUpForm__body">
        <div className="signUpForm__inputs">
          <div className="signUpForm__field">
            <p>First Name</p>
            <div className="signUpForm__input">
              <FormInput/>
            </div>
          </div>

          <div className="signUpForm__field">
            <p>Last Name</p>
            <div className="signUpForm__input">
              <FormInput/>
            </div>
          </div>

          <div className="signUpForm__field">
            <p>Phone number</p>
            <div className="signUpForm__input">
              <FormInput type="phone"/>
            </div>
          </div>

          <div className="signUpForm__field">
            <p>E-mail</p>
            <div className="signUpForm__input">
              <FormInput type="mail"/>
            </div>
          </div>

          <div className="signUpForm__field">
            <p>Password</p>
            <div className="signUpForm__input">
              <FormInput type="password"/>
            </div>
          </div>

          <div className="signUpForm__field">
            <p>Repeat password</p>
            <div className="signUpForm__input">
              <FormInput type="password"/>
            </div>
          </div>
        </div>

        <div className="signUpForm__checkBoxes">
          <CheckBox state={!checkBoxState} title="I Executor" onChange={handleCheckBoxChange}/>
          <CheckBox state={checkBoxState} title="I Vendor" onChange={handleCheckBoxChange}/>
        </div>
      </div>

      <div className="signUpForm__footer">
        <input type="submit" value="Sign up"/>

        <div className="signUpForm__already">
          <p>Already have an account?</p>
          <NavLink to="/sign-in">
            Sign in
          </NavLink>
        </div>
      </div>
    </form>
  );
};

export default SignUpForm;