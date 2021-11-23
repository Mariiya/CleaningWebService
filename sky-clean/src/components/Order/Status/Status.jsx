//general
import React from 'react'
//styles
import './Status.scss'
//assets
import {ReactComponent as InProgressIcon} from "../../../assets/icons/orderStatusInProgress.svg";
import {ReactComponent as IsOpenIcon} from "../../../assets/icons/orderStatusCompeleted.svg";
import {ReactComponent as CanceledIcon} from "../../../assets/icons/orderStatusCanceled.svg";
import {ReactComponent as RejectedIcon} from "../../../assets/icons/orderStatusRejected.svg";

function Status({status}) {
  switch (status) {
    case 'STATUS_OPEN':
      return (
        <div className="status" style={{backgroundColor: '#91DFA2'}}>
          <div className="status__imgContainer">
            <IsOpenIcon/>
          </div>
          
          <h3 className="status__title">
            Open
          </h3>
        </div>
      )
    
    case 'STATUS_IN_PROGRES':
      return (
        <div className="status" style={{backgroundColor: '#A5A6F6'}}>
          <div className="status__imgContainer">
            <InProgressIcon/>
          </div>
    
          <h3 className="status__title">
            In progress
          </h3>
        </div>
      )
  
    case 'STATUS_CANCELED':
      return (
        <div className="status" style={{backgroundColor: '#D9C456'}}>
          <div className="status__imgContainer">
            <CanceledIcon/>
          </div>
        
          <h3 className="status__title">
            Canceled
          </h3>
        </div>
      )
  
    case 'STATUS_SUSPENDED':
      return (
        <div className="status" style={{backgroundColor: '#D9C456'}}>
          <div className="status__imgContainer">
            <CanceledIcon/>
          </div>
        
          <h3 className="status__title">
            Suspended
          </h3>
        </div>
      )
  
    case 'STATUS_REJECTED':
      return (
        <div className="status" style={{backgroundColor: '#D49C9C'}}>
          <div className="status__imgContainer">
            <RejectedIcon/>
          </div>
        
          <h3 className="status__title">
            Rejected
          </h3>
        </div>
      )
    
    
    default:
      return null
  }
}

export default Status