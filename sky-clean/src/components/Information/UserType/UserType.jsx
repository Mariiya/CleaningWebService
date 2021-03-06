//general
import React from 'react';
import {Link} from "react-router-dom";
//styles
import './UserType.scss';
//assets
import {ReactComponent as RightArrow} from "../../../assets/icons/userType-arrow-right.svg";

function UserType({userType}) {
  return (
    <div className="userType">
      <div className="userType__img-container">
        <img
          className="userType__img"
          src={userType.src}
          alt={userType.type}
        />
      </div>
      <div className="userType__data">
        <h3 className="userType__title">
          For {userType.type}
        </h3>
        <h4 className="userType__description">
          {userType.description} :
        </h4>
        <Link
          className="userType__link"
          to={`/${userType.link}`}>
          Go to {userType.type}
          <RightArrow/>
        </Link>
      </div>
    </div>
  );
};

export default UserType;