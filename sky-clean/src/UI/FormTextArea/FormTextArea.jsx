//general
import React from 'react';
//styles
import './FormTextArea.scss';

function FormTextArea({placeholder = "", type = "text", errors, ...rest}) {
  const errorMsg = {
    display: "flex",
    justifyContent: "flex-end",
    fontSize: "12px",
    padding: "5px 10px",
    color: "#de0202",
  }
  
  const errorBorder = {
    border: "1px solid #de0202"
  }
  
  return (
    <>
      <textarea
        className="formTextArea"
        style={errors && errorBorder}
        type={type}
        placeholder={placeholder}
        {...rest}/>
      <p style={errorMsg}>{errors}</p>
    </>
  );
};

export default FormTextArea