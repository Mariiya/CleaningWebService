//general
import React from 'react'
//styles
import './ServiceCheckBox.scss'
//assets
import {ReactComponent as CheckBoxIcon} from "../../../assets/icons/service-checkBox.svg";

function ServiceCheckBox({title}) {
  const [checkBoxState, setCheckBoxState] = React.useState(false)
  
  const handleCheckBoxChange = () => {
    setCheckBoxState(!checkBoxState)
  }
  
  return (
    <div className="serviceCheckBox" onClick={handleCheckBoxChange}>
      <div className={checkBoxState ? "serviceCheckBox__checkBox serviceCheckBox__checkBox_active" : "serviceCheckBox__checkBox"}>
        <CheckBoxIcon/>
      </div>
      <p className="serviceCheckBox__title">
        {title}
      </p>
    </div>
  )
}

export default ServiceCheckBox