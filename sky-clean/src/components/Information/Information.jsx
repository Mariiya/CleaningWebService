//general
import React from 'react';
//components
import UserType from "./UserType/UserType";
//styles
import './Information.scss';
//assets
import customer from '../../assets/icons/information-customer.svg';
import specialist from '../../assets/icons/information-specialist.svg';

function Information() {
  const userTypes = [
    {src: customer, type: 'customer', description: 'If you would like to become our Client, just follow the link below'},
    {src: specialist, type: 'specialist', description: 'If you would like to become our Specialist, just follow the link below'},
  ];

  return (
    <div className="information">
      <div className="information__wrapper wrapper">
        <div className="information__list">
          {userTypes.map((userType) => <UserType key={userType.type} userType={userType}/>)}
        </div>
      </div>
    </div>
  );
};

export default Information;