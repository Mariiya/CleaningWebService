//general
import React from 'react';
//components
import SignInForm from "../../components/SignInForm/SignInForm";
//styles
import './SignInPage.scss';

function SignInPage() {
  return (
    <div className="signInPage">
      <SignInForm/>
    </div>
  );
};

export default SignInPage;