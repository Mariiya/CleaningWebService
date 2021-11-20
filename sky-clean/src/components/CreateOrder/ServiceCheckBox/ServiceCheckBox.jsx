//general
import React from 'react'
import {useDispatch} from "react-redux";
//redux
import {setServiceCheck} from "../../../store/services/actions";
//styles
import './ServiceCheckBox.scss'
//assets
import {ReactComponent as CheckBoxIcon} from "../../../assets/icons/service-checkBox.svg";

function ServiceCheckBox({service}) {
  const dispatch = useDispatch()
  
  const [checkBoxState, setCheckBoxState] = React.useState(false)
  
  const handleCheckBoxChange = (id) => {
    setCheckBoxState(!checkBoxState)
    dispatch(setServiceCheck(id))
  }
  
  return (
    <div className="serviceCheckBox" onClick={() => handleCheckBoxChange(service.id)}>
      <div className={service.state ? "serviceCheckBox__checkBox serviceCheckBox__checkBox_active" : "serviceCheckBox__checkBox"}>
        <CheckBoxIcon/>
      </div>
      <p className="serviceCheckBox__title">
        {service.name}
      </p>
    </div>
  )
}

export default ServiceCheckBox