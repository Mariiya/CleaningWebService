//general
import React from 'react';
//components
import Benefit from "./Benefit/Benefit";
//styles
import './Benefits.scss';
//assets
import feedback from '../../assets/icons/benefit-feedback.svg';
import experience from '../../assets/icons/benefits-experiensed.svg';
import orders from '../../assets/icons/benefit-orders.svg';

function Benefits() {
  const benefits = [
    {src: feedback, title: 'Convenient feedback'},
    {src: experience, title: 'Thousands of experienced Specialists'},
    {src: orders, title: 'Hundreds of profitable orders'}
  ];

  return (
    <div className="benefits">
      <div className="benefits__wrapper wrapper">
        <h2 className="benefits__title">
          Our benefits
        </h2>
        <div className="benefits__list">
          {benefits.map((benefit) => <Benefit key={benefit.title} benefit={benefit}/>)}
        </div>
      </div>
    </div>
  );
};

export default Benefits;