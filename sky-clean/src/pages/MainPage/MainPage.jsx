//general
import React from 'react';
//components
import General from '../../components/General/General';
import Services from "../../components/Services/Services";
import Benefits from "../../components/Benefits/Benefits";
import Work from "../../components/Work/Work";
import Information from "../../components/Information/Information";
//styles
import './MainPage.scss'

function MainPage() {
  return (
    <div className="mainPage">
      <General/>
      <Services/>
      <Benefits/>
      <Work/>
      <Information/>
    </div>
  )
}

export default MainPage;