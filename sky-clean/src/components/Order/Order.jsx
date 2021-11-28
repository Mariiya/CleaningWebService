//general
import React from 'react'
//api
import {assignOrder, getOrder, setOrderInProgress} from "../../api/orders.api";
//components
import Detail from "./Detail/Detail";
//styles
import './Order.scss'
import Status from "./Status/Status";
import Service from "./Service/Service";
import Spinner from "../../UI/Spinner/Spinner";
import {useSelector} from "react-redux";
import {notify} from "../../helpers/notify/notify";
import {useHistory} from "react-router";

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
  
  const history = useHistory()
  
  const userInfo = useSelector((state) => state.user.userInfo)
  
  
  const assignOrderByVendor = (userId, orderId) => {
    assignOrder(userId, orderId).then((response) => {
      if (response) {
        setOrderInProgress(orderId).then((response) => {
          if (response === true) {
            notify('Success', 'You successfully assigned this order!')
            history.push('/account')
          }
        })
      }
    })
  }
  
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
      
              {userInfo.role !== 'ROLE_CLIENT' && <button className="orderDetail__acceptBtn" onClick={() => assignOrderByVendor(userInfo.id, order.id)}>
                Accept
              </button>}
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

export default Order