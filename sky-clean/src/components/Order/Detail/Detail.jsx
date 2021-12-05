//general
import React from 'react'
//ui
import FormInput from "../../../UI/FormInput/FormInput";
//styles
import './Detail.scss'

function Detail({title, description, isEditable, value, onChange, error}) {
  return (
    <div className="detail">
      <h3 className="detail__title">
        {title}
      </h3>

        {isEditable ? (
          <div className="detail__editableDetail">
              <div className="detail__formField">
                  <FormInput name={`${title.toLowerCase()}`} value={value} onChange={onChange} errors={error}/>
              </div>
          </div>
        ) : (
          <h4 className="detail__description">
            {description}
          </h4>
        )}
    </div>
  )
}

export default Detail