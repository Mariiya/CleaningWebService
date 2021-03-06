//general
import React from 'react'
import {useDispatch} from "react-redux";
//redux
import {setOneServiceCheck} from "../../../store/services/actions";
//styles
import './Service.scss'
//assets
import {ReactComponent as ServiceIcon} from "../../../assets/icons/service-checkBox.svg";

function Service({service, setSelectedService}) {
  const dispatch = useDispatch()
  
  const [checkBoxState, setCheckBoxState] = React.useState(false)
  
  const handleCheckBoxChange = (id) => {
    setCheckBoxState(!checkBoxState)
    dispatch(setOneServiceCheck(id))
    setSelectedService(id)
  }
  
  return (
    <div className="filterService" onClick={() => handleCheckBoxChange(service.id)}>
      <div className={service.state ? 'filterService__imgContainer filterService__imgContainer_active' : 'filterService__imgContainer'}>
        <ServiceIcon style={{width: "100%", height: "100%"}}/>
      </div>
      <h3 className="filterService__title">
        {service.name}
      </h3>
    </div>
  )
}

export default Service