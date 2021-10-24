//general
import React from 'react';
//components
import Step from './Step/Step';
//styles
import './Work.scss';
//assets
import createOrder from '../../assets/icons/step-create.svg';
import waitSpecialist from '../../assets/icons/step-wait.svg';
import closeOrder from '../../assets/icons/step-close.svg';
import {ReactComponent as ArrowRight} from "../../assets/icons/step-right-arrow.svg";

function Work() {
  const steps = [
    {src: createOrder, title: 'Create order', description: 'Describe what and when need to do'},
    {src: waitSpecialist, title: 'Wait specialist', description: 'We will find for you a competent specialist for any of your tasks'},
    {src: closeOrder, title: 'Close order', description: 'Write a review and rating for the work done'},
  ];

  return (
    <div className="work">
      <div className="work__wrapper wrapper">
        <h2 className="work__title">
          How is it works ?
        </h2>
        <div className="work__list">
          {steps.map((step, index) =>
            <React.Fragment key={step.title}>
              <Step step={step}/>
              <ArrowRight className="work__arrow"/>
            </React.Fragment>
          )}
        </div>
      </div>
    </div>
  );
};

export default Work;