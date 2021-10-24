//general
import React from 'react';
//components
import Palette from './Palette/Palette';
//styles
import './General.scss';
//assets
import GeneralImage from "../../assets/images/general-image.svg";
import TakeOrder from "../../assets/icons/general-take-order.svg";
import CreateOrder from "../../assets/icons/general-create-order.svg";

function General() {
  const palettes = [
    {link: '#', linkTitle: 'Create order', src: CreateOrder},
    {link: '#', linkTitle: 'Take order', src: TakeOrder}];

  return (
    <div className="general">
      <div className="general__wrapper wrapper">
        <div className="general__info-container">
          <div className="general__data">
            <h1 className="general__data-title">
              Web service for ordering cleaning services
            </h1>
            <h2 className="general__data-description">
              More than a thousand proven specialists to carry out your everyday tasks!
            </h2>
            <div className="general__data-btn-container">
              What should I do?
              <a
                href="/#"
                className="general__data-btn">
                Search Specialist
              </a>
            </div>
          </div>
          <div className="general__image-container">
            <img
              src={GeneralImage}
              alt="general"
              className="general__image"/>
          </div>
        </div>
        <div className="general__palettes">
          {palettes.map((palette) => <Palette key={palette.linkTitle} palette={palette}/>)}
        </div>
      </div>
    </div>
  );
};

export default General;