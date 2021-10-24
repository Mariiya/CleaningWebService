//general
import React from 'react';
//styles
import './Benefit.scss';

function Benefit({benefit}) {
  return (
    <div className="benefit">
      <div className="benefit__img-container">
        <img
          className="benefit__img"
          src={benefit.src}
          alt={benefit.title}
        />
      </div>
      <h3 className="benefit__title">
        {benefit.title}
      </h3>
    </div>
  );
};

export default Benefit;