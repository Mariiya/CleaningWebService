//general
import React from 'react';
//styles
import './Palette.scss'
//assets
import {ReactComponent as ArrowRight} from "../../../assets/icons/order-arrow-right.svg";

function Palette({palette}) {
  return (
    <div className="palette">
      <div className="palette__link-container">
        <a href={`/${palette.link}`}
          className="palette__link">
          {palette.linkTitle}
          <ArrowRight/>
        </a>
      </div>
      <div className="palette__img-container">
        <img
          className="palette__img"
          src={palette.src}
          alt={palette.linkTitle}
        />
      </div>
    </div>
  );
};

export default Palette;