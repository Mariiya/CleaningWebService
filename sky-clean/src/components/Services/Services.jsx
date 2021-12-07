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

function Services() {

  const services = [
    {src: cleaningApartments, title: 'Cleaning of apartments'},
    {src: springCleaning, title: 'Spring-cleaning'},
    {src: cleaningRenovation, title: 'Cleaning after renovation'},
    {src: dryCleaning, title: 'Dry cleaning'},
    {src: cleaningCottages, title: 'Cleaning of cottages'},
    {src: washingWindows, title: 'Washing windows'},
    {src: territoryCleaning, title: 'Territory cleaning'},
    {src: ecoCleaning, title: 'Eco cleaning'},
    {src: facadeWashing, title: 'Facade washing'},
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
      </div>
    </div>
  );
};

export default Services;