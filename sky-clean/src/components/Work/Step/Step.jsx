//general
import React from 'react';
//styles
import './Step.scss';

function Step({step}) {
  return (
    <div className="step">
      <div className="step__img-container">
        <img
          className="step__img"
          src={step.src}
          alt={step.title}
        />
      </div>
      <h3 className="step__title">
        {step.title}
      </h3>
      <h4 className="step__description">
        {step.description}
      </h4>
    </div>
  );
};

export default Step;