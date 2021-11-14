//general
import React from 'react';
//styles
import './FormInput.scss';

function FormInput({placeholder = "", type = "text", errors, ...rest}) {
  const errorMsg = {
    display: "flex",
    justifyContent: "flex-end",
    fontSize: "12px",
    padding: "0 10px",
    color: "#de0202",
  }
  
  const errorBorder = {
    border: "1px solid #de0202"
  }
  
  return (
    <>
      <input
        className="formInput"
        style={errors && errorBorder}
        type={type}
        placeholder={placeholder}
        {...rest}/>
      <p style={errorMsg}>{errors}</p>
    </>
  );
};

export default FormInput