//general
import React from 'react'
//styles
import './NotFound.scss'
//assets
import {ReactComponent as NotFoundImage} from "../../assets/images/not-found.svg";

function NotFound() {
  return (
    <div className="notFound">
      <div className="notFound__wrapper wrapper">
        <div className="notFound__data">
          <h1 className="notFound__errorName">
            404
          </h1>
          
          <h2 className="notFound__title">
            OOPS! THIS PAGE COULD NOT BE FOUND
          </h2>
          
          <h3 className="notFound__description">
            Sorry, but the page are you looking for does not exist, have been removed, name changed or is temporarily unavailable
          </h3>
        </div>
        
        <div className="notFound__imageContainer">
          <NotFoundImage/>
        </div>
      </div>
    </div>
  )
}

export default NotFound