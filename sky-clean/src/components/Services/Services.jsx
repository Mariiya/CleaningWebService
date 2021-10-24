//general
import React from 'react';
//components
import Service from "./Service/Service";
//styles
import './Services.scss';
//assets
import cleaningApartments from '../../assets/icons/cleaning-apartments.svg';
import springCleaning from '../../assets/icons/spring-cleaning.svg';
import cleaningRenovation from '../../assets/icons/cleaning-renovation.svg';
import dryCleaning from '../../assets/icons/dry-cleaning.svg';
import cleaningCottages from '../../assets/icons/cleaning-cottages.svg';
import washingWindows from '../../assets/icons/washing-windows.svg';
import territoryCleaning from '../../assets/icons/territory-cleaning.svg';
import ecoCleaning from '../../assets/icons/eco-cleaning.svg';
import facadeWashing from '../../assets/icons/facade-washing.svg';
import {ReactComponent as DownArrow} from "../../assets/icons/services-arrow-down.svg";

function Services() {
  const services = [
    {src: cleaningApartments, title: 'Cleaning of apartments', count: 15046},
    {src: springCleaning, title: 'Spring-cleaning', count: 11487},
    {src: cleaningRenovation, title: 'Cleaning after renovation', count: 11621},
    {src: dryCleaning, title: 'Dry cleaning', count: 1730},
    {src: cleaningCottages, title: 'Cleaning of cottages', count: 10293},
    {src: washingWindows, title: 'Washing windows', count: 10938},
    {src: territoryCleaning, title: 'Territory cleaning', count: 10061},
    {src: ecoCleaning, title: 'Eco cleaning', count: 1775},
    {src: facadeWashing, title: 'Facade washing', count: 1996},
  ];

  return (
    <div className="services">
      <div className="services__wrapper wrapper">
        <h2 className="services__title">
          Types of services
        </h2>
        <div className="services__list">
          {services.map((service) => <Service key={service.title} service={service}/> )}
        </div>
        <div className="services__more-container">
          <DownArrow className="services__more"/>
        </div>
      </div>
    </div>
  );
};

export default Services;