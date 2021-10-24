//general
import React from 'react';
//styles
import './CheckBox.scss';
import {ReactComponent as Switcher} from '../../../assets/icons/sign-up-check-box.svg';

function CheckBox({state = false, title = '', onChange}) {
  return (
    <div className="checkBox" onClick={onChange}>
      <div className={state ? "checkBox__switch checkBox__switch_active" : "checkBox__switch"}>
        <Switcher/>
      </div>

      <p>{title}</p>
    </div>
  );
};

export default CheckBox;