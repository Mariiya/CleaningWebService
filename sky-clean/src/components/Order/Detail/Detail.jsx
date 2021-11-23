//general
import React from 'react'
//styles
import './Detail.scss'

function Detail({title, description}) {
  return (
    <div className="detail">
      <h3 className="detail__title">
        {title}
      </h3>
    
      <h4 className="detail__description">
        {description}
      </h4>
    </div>
  )
}

export default Detail