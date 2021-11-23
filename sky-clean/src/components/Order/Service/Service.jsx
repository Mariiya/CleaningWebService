//general
import React from 'react'
//styles
import './Service.scss'
//assets
import {ReactComponent as ServiceIcon} from "../../../assets/icons/service-checkBox.svg";

function Service({service}) {
  return (
    <div className="serviceDetail">
      <div className="serviceDetail__imgContainer">
        <ServiceIcon/>
      </div>
      <h3 className="serviceDetail__title">
        {service.name}
      </h3>
    </div>
  )
}

export default Service