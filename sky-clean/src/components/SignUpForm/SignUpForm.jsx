//general
import React from 'react';
import {NavLink} from "react-router-dom";
//ui
import Spinner from "../../UI/Spinner/Spinner";
import FormInput from "../../UI/FormInput/FormInput";
import CheckBox from "./CheckBox.jsx/CheckBox";
//styles
import './SignUpForm.scss';

function SignUpForm({values, errors, formik, handleChange, setUserRole, userRole, vendorType, setVendorType, loading}) {
  const handleUserRoleChange = () => {
    setUserRole(!userRole)
  }
  
  const handleVendorTypeChange = () => {
    setVendorType(!vendorType)
  }

  return (
    !loading ? (
      <form className="signUpForm" onSubmit={formik.handleSubmit}>
        <div className="signUpForm__body">
          <div className="signUpForm__inputs">
            <div className="signUpForm__field">
              <p>First Name</p>
              <div className="signUpForm__input">
                <FormInput name="firstName" value={values.firstName} onChange={handleChange} errors={errors.firstName}/>
              </div>
            </div>
        
            <div className="signUpForm__field">
              <p>Last Name</p>
              <div className="signUpForm__input">
                <FormInput name="lastName" value={values.lastName} onChange={handleChange} errors={errors.lastName}/>
              </div>
            </div>
        
            <div className="signUpForm__field">
              <p>Phone number</p>
              <div className="signUpForm__input">
                <FormInput type="phone" name="phoneNumber" value={values.phoneNumber} onChange={handleChange} errors={errors.phoneNumber}/>
              </div>
            </div>
        
            <div className="signUpForm__field">
              <p>E-mail</p>
              <div className="signUpForm__input">
                <FormInput type="email" name="email" value={values.email} onChange={handleChange} errors={errors.email}/>
              </div>
            </div>
        
            <div className="signUpForm__field">
              <p>Password</p>
              <div className="signUpForm__input">
                <FormInput type="password" name="password" value={values.password} onChange={handleChange} errors={errors.password}/>
              </div>
            </div>
        
            <div className="signUpForm__field">
              <p>Repeat password</p>
              <div className="signUpForm__input">
                <FormInput type="password" name="repeatPassword" value={values.repeatPassword} onChange={handleChange} errors={errors.repeatPassword}/>
              </div>
            </div>
          </div>
      
          <div className="signUpForm__checkBoxes">
            <CheckBox state={!userRole} title="I Consumer" onChange={handleUserRoleChange}/>
            <CheckBox state={userRole} title="I Vendor" onChange={handleUserRoleChange}/>
          </div>
      
          {userRole && (
            <div className="signUpForm__checkBoxes">
              <CheckBox state={vendorType} title='I individual' onChange={handleVendorTypeChange}/>
            </div>
          )}
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
    ) : (
      <div className="spinnerContainer">
        <Spinner/>
      </div>
    )
  );
};

export default SignUpForm;