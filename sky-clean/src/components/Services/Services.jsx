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
import {getCountOrders} from "../../api/orders.api";

function Services() {
  const [servicesCount, setServicesCount] = React.useState({
    cleaningApartments: 0,
    springCleaning: 0,
    cleaningRenovation: 0,
    dryCleaning: 0,
    cleaningCottages: 0,
    washingWindows: 0,
    territoryCleaning: 0,
    ecoCleaning: 0,
    facadeWashing: 0,
  })

  React.useEffect(() => {
    getCountOrders(0, 0, '', 37).then((response) => {
      setServicesCount({...servicesCount, cleaningApartments: response})
    })
    getCountOrders(0, 0, '', 38).then((response) => {
      setServicesCount({...servicesCount, springCleaning: response})
    })
    getCountOrders(0, 0, '', 39).then((response) => {
      setServicesCount({...servicesCount, cleaningRenovation: response})
    })
    getCountOrders(0, 0, '', 40).then((response) => {
      setServicesCount({...servicesCount, dryCleaning: response})
    })
    getCountOrders(0, 0, '', 41).then((response) => {
      setServicesCount({...servicesCount, cleaningCottages: response})
    })
    getCountOrders(0, 0, '', 42).then((response) => {
      setServicesCount({...servicesCount, washingWindows: response})
    })
    getCountOrders(0, 0, '', 43).then((response) => {
      setServicesCount({...servicesCount, territoryCleaning: response})
    })
    getCountOrders(0, 0, '', 44).then((response) => {
      setServicesCount({...servicesCount, ecoCleaning: response})
    })
    getCountOrders(0, 0, '', 45).then((response) => {
      setServicesCount({...servicesCount, facadeWashing: response})
    })
    // eslint-disable-next-line
  }, [])

  const services = [
    {src: cleaningApartments, title: 'Cleaning of apartments', count: servicesCount.cleaningApartments},
    {src: springCleaning, title: 'Spring-cleaning', count: servicesCount.springCleaning},
    {src: cleaningRenovation, title: 'Cleaning after renovation', count: servicesCount.cleaningRenovation},
    {src: dryCleaning, title: 'Dry cleaning', count: servicesCount.dryCleaning},
    {src: cleaningCottages, title: 'Cleaning of cottages', count: servicesCount.cleaningCottages},
    {src: washingWindows, title: 'Washing windows', count: servicesCount.washingWindows},
    {src: territoryCleaning, title: 'Territory cleaning', count: servicesCount.territoryCleaning},
    {src: ecoCleaning, title: 'Eco cleaning', count: servicesCount.ecoCleaning},
    {src: facadeWashing, title: 'Facade washing', count: servicesCount.facadeWashing},
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