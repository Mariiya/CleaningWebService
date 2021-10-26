//general
import React from 'react';
import {Link} from "react-router-dom";
//styles
import './Palette.scss'
//assets
import {ReactComponent as ArrowRight} from "../../../assets/icons/order-arrow-right.svg";

function Palette({palette}) {
  return (
    <Link className="palette" to={`/${palette.link}`}>
      <div className="palette__link-container">
        <h3
          className="palette__link">
          {palette.linkTitle}
          <ArrowRight/>
        </h3>
      </div>
      <div className="palette__img-container">
        <img
          className="palette__img"
          src={palette.src}
          alt={palette.linkTitle}
        />
      </div>
    </Link>
  );
};

export default Palette;