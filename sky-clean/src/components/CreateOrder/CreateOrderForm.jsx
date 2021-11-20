//general
import React from 'react'
import {useSelector} from "react-redux";
//ui
import FormInput from "../../UI/FormInput/FormInput";
import FormTextArea from "../../UI/FormTextArea/FormTextArea";
//ui
import Spinner from "../../UI/Spinner/Spinner";
//components
import ServiceCheckBox from "./ServiceCheckBox/ServiceCheckBox";
import CustomServiceFormEditor from "./CustomService/CustomServiceFormEditor";
//styles
import './CreateOrderForm.scss'

function CreateOrderForm({values, errors, form, handleChange}) {
  const [showForm, setShowForm] = React.useState(false)
  const services = useSelector((state) => state.services.services)
  
  const handleShowFormOpenClick = () => {
    setShowForm(true)
  }
  
  const handleShowFormCloseClick = () => {
    setShowForm(false)
  }
  
  return (
    <div className="createOrderForm__container">
      <form className="createOrderForm" onSubmit={form.handleSubmit}>
        <div className="createOrderForm__header">
          <h3 className="createOrderForm__title">
            Create your order
          </h3>
        </div>
    
        <div className="createOrderForm__body">
          <div className="createOrderForm__field">
            <p className="createOrderForm__fieldName">
              Order name
            </p>
        
            <div className="createOrderForm__input">
              <FormInput placeholder="Briefly name your order" name="name" value={values.name} errors={errors.name} onChange={handleChange}/>
            </div>
          </div>
      
          <div className="createOrderForm__servicesField">
            <p className="createOrderForm__servicesFieldName">
              Services
            </p>
        
            <div className="createOrderForm__services">
              {services?.length ? (
                <div className="createOrderForm__servicesContainer">
                  {services.map((service) => (
                    <ServiceCheckBox key={service.name} service={service}/>
                  ))}
                </div>
              ) : (
                <div className="spinnerContainer">
                  <Spinner/>
                </div>
              )
              }
            </div>
          </div>
      
          <div className="createOrderForm__customService" onClick={handleShowFormOpenClick}>
            Create custom service
          </div>
      
          <div className="createOrderForm__inputField">
            <p className="createOrderForm__fieldName">
              Description
            </p>
            <div className="createOrderForm__textArea">
              <FormTextArea placeholder="Describe details of your order" name="description" value={values.description} errors={errors.description} onChange={handleChange}/>
            </div>
          </div>
      
          <div className="createOrderForm__field">
            <p className="createOrderForm__fieldName">
              Address
            </p>
        
            <div className="createOrderForm__input">
              <FormInput placeholder="Write your address here" name="address" value={values.address} errors={errors.address} onChange={handleChange}/>
            </div>
          </div>
      
          <div className="createOrderForm__field">
            <p className="createOrderForm__fieldName">
              Price
            </p>
        
            <div className="createOrderForm__input">
              <FormInput placeholder="Indicate price in UAH" name="price" value={values.price} errors={errors.price} onChange={handleChange}/>
            </div>
          </div>
        </div>
    
        <div className="createOrderForm__footer">
          <button type="submit" className="createOrderForm__submitBtn">
            Submit
          </button>
        </div>
      </form>
      {showForm && (
        <CustomServiceFormEditor handleShowFormCloseClick={handleShowFormCloseClick}/>
      )}
    </div>
  )
}

export default CreateOrderForm