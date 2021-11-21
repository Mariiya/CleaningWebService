//general
import React from 'react'
//api
import {getOrder} from "../../api/orders.api";
//components
import Detail from "./Detail/Detail";
//styles
import './Order.scss'
import Status from "./Status/Status";
import Service from "./Service/Service";
import Spinner from "../../UI/Spinner/Spinner";

function Order({id}) {
  const [loading, setLoading] = React.useState(false)
  const [order, setOrder] = React.useState(null)
  
  React.useEffect(() => {
    setLoading(true)
    getOrder(id).then((response) => {
      setLoading(false)
      setOrder(response)
    })
    return () => {
      setOrder(null)
    }
  }, [id])
  
  return (
    <div className="orderDetail">
      <div className="orderDetail__wrapper wrapper">
        {loading ? (
          <div className="spinnerContainer">
            <Spinner/>
          </div>) : (
          <div className="orderDetail__container">
            <div className="orderDetail__header">
              <div className="orderDetail__titleAndState">
                <h3 className="orderDetail__title">
                  {order?.title} (Order #{order?.id})
                </h3>
        
                <Status status={order?.status}/>
              </div>
      
              <h4 className="orderDetail__consumer">
                {order?.consumer.firstName} {order?.consumer.lastName}
              </h4>
            </div>
    
            <div className="orderDetail__body">
              <div className="orderDetail__details">
                <Detail title="Description" description={order?.description}/>
                <Detail title="Address" description={order?.address}/>
        
                <div className="orderDetail__services">
                  <h3 className="orderDetail__servicesTitle">Services</h3>
          
                  <div className="orderDetail__servicesContainer">
                    {order?.services.map((service) => (
                      <Service key={service.id} service={service}/>
                    ))}
                  </div>
                </div>
              </div>
            </div>
    
            <div className="orderDetail__footer">
              <h3 className="orderDetail__price">
                {order?.price}
              </h3>
      
              <button className="orderDetail__acceptBtn">
                Accept
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

export default Order