//general
import React from 'react'
//components
import UserInfo from "../UserInfo/UserInfo";
//styles
import './Account.scss'
import UserOrders from "../UserOrders/UserOrders";

function Account() {
  const [activeTab, setActiveTab] = React.useState('Main')
  
  const handleSwitchActiveTab = (event) => {
    const tabName = event.target.outerText
    setActiveTab(tabName)
  }
  
  return (
    <div className="account">
      <div className="account__wrapper wrapper">
        <div className="account__switch">
          <button
            className={activeTab === 'Main' ? "account__switchBtn account__switchBtn_active" : "account__switchBtn"}
            onClick={(event) => handleSwitchActiveTab(event)}>
            Main
          </button>
  
          <button
            className={activeTab === 'Orders' ? "account__switchBtn account__switchBtn_active" : "account__switchBtn"}
            onClick={(event) => handleSwitchActiveTab(event)}>
            Orders
          </button>
        </div>
        <div className="account__content">
          {activeTab === 'Main' ? <UserInfo/> : <UserOrders/>}
        </div>
      </div>
    </div>
  )
}

export default Account