//general
import React from 'react';
//styles
import './FormInput.scss';

function FormInput({onChange, value, placeholder = "", type = "text"}) {
  return (
    <input
      className="formInput"
      type={type}
      placeholder={placeholder}
      onChange={(event) => onChange(event.target.value)}
      value={value}/>
  );
};

export default FormInput