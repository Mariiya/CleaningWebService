//general
import React from 'react'
//components
import UserDataFormEditor from "./UserDataForm/UserDataFormEditor";
//styles
import './UserInfo.scss'
//assets
import {ReactComponent as UserImage} from "../../assets/images/account-image.svg";
import UserPasswordFormEditor from "./UserPasswordForm/UserPasswordFormEditor";

function UserInfo() {
  return (
    <div className="userInfo">
      <div className="userInfo__data">
        <UserDataFormEditor/>
        <div className="userInfo__imgContainer">
          <UserImage/>
        </div>
      </div>
      
      <div className="userInfo__password">
        <UserPasswordFormEditor/>
      </div>
    </div>
  )
}

export default UserInfo