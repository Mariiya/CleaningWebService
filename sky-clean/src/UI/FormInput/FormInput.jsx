//general
import React from 'react';
//styles
import './FormInput.scss';

function FormInput({placeholder = "", type = "text", ...rest}) {
  return (
    <input
      className="formInput"
      type={type}
      placeholder={placeholder}
      {...rest}/>
  );
};

export default FormInput