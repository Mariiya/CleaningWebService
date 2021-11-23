//general
import React from 'react'
import {useSelector} from "react-redux";
//components
import Status from "../../Order/Status/Status";
import Detail from "../../Order/Detail/Detail";
import Service from "../../Order/Service/Service";
//styles
import './Order.scss'
//assets
import {ReactComponent as ViewMoreDetailSvg} from "../../../assets/icons/services-arrow-down.svg";

function Order({order}) {
  const userInfo = useSelector((state) => state.user.userInfo)
  
  const [showDetails, setShowDetails] = React.useState(false)
  
  const handleSetShowDetails = () => {
    setShowDetails(!showDetails)
  }
  
  return (
    <div className="userOrder">
      <div className="userOrder__wrapper">
        <div className="userOrder__header">
          <div className="userOrder__titleAndState">
            <h3 className="userOrder__title">
              {order?.title} (Order #{order?.id})
            </h3>
    
            <Status status={order?.status}/>
          </div>
  
          <div className="userOrder__consumer">
            <p> {order?.consumer.firstName} {order?.consumer.lastName} </p>
            <p> {order?.vendor.firstName !== null &&  order.vendor.lastName !== null ? `${order.vendor.firstName} ${order.vendor.lastName}` : 'No vendor' } </p>
          </div>
        </div>
        
        <div className="userOrder__body">
          <div className="userOrder__details">
            <Detail title="Description" description={order?.description}/>
            <Detail title="Address" description={order?.address}/>
  
            <h3 className="userOrder__price">
              {order?.price}
            </h3>
          </div>
          
          <div className="userOrder__extraDetails">
            {showDetails ? <ViewMoreDetailSvg onClick={handleSetShowDetails} className="userOrder__hideDetail"/> : <ViewMoreDetailSvg onClick={handleSetShowDetails}/>}
          </div>
          
          {showDetails && (
            <>
              <div className="userOrder__services">
                <h3 className="userOrder__servicesTitle">Services</h3>
    
                <div className="userOrder__servicesContainer">
                  {order?.services.map((service) => (
                    <Service key={service.id} service={service}/>
                  ))}
                </div>
              </div>
                {userInfo.role === 'ROLE_CLIENT' && order?.vendor.firstName !== null && (
                  <div className="userOrder__vendorConsumerInfo">
                    <div className="userOrder__vendorConsumerName">
                      <h3>Vendor name</h3>
                      <h4>{order?.vendor.firstName} {order?.vendor.lastName}</h4>
                    </div>
                    <div className="userOrder__vendorConsumerPhone">
                      <h3>Vendor phone number</h3>
                      <h4>{order?.vendor.phoneNumber}</h4>
                    </div>
                  </div>
                )}
                {userInfo.role === 'ROLE_SERVICE_PROVIDER' && (
                  <div className="userOrder__vendorConsumerInfo">
                    <div className="userOrder__vendorConsumerName">
                      <h3>Consumer name</h3>
                      <h4>{order?.consumer.firstName} {order?.consumer.lastName}</h4>
                    </div>
                    <div className="userOrder__vendorConsumerPhone">
                      <h3>Consumer phone number</h3>
                      <h4>{order?.consumer.phoneNumber}</h4>
                    </div>
                  </div>
                )}
            </>
          )}
        </div>
          {userInfo.role === 'ROLE_CLIENT' && (
            <div className="userOrder__footer">
              <button className="userOrder__complete">
                Complete
              </button>
  
              <button className="userOrder__reject">
                Reject
              </button>
            </div>
          )}
  
          {userInfo.role === 'ROLE_SERVICE_PROVIDER' && (
            <div className="userOrder__footer">
              <button className="userOrder__cancel">
                Cancel
              </button>
            </div>
          )}
      </div>
    </div>
  )
}

export default Order