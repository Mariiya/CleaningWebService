//general
import React from 'react';
//styles
import './Service.scss';

function Service({service}) {
  return (
    <div className="service">
      <div className="service__img-container">
        <img
          className="service__img"
          src={service.src}
          alt={service.title}
        />
      </div>
      <h3 className="service__title">
        {service.title}
      </h3>
    </div>
  );
};

export default Service;