//general
import React from 'react'
import {useDispatch, useSelector} from "react-redux";
//api
import {setOrderCanceled, setOrderComplete, setOrderDelete, setOrderReject} from "../../../api/orders.api";
//redux
import {deleteOrder, setOrderStatus} from "../../../store/orders/actions";
//helpers
import {notify} from "../../../helpers/notify/notify";
//ui
import FormInput from "../../../UI/FormInput/FormInput";
//components
import Status from "../../Order/Status/Status";
import Detail from "../../Order/Detail/Detail";
import Service from "../../Order/Service/Service";
//styles
import './Order.scss'
//assets
import {ReactComponent as ViewMoreDetailSvg} from "../../../assets/icons/services-arrow-down.svg";

function Order({order, values, errors, form, handleChange, handleEditOrderData, editableOrderData}) {
  const dispatch = useDispatch()
  const userInfo = useSelector((state) => state.user.userInfo)
  
  const [showDetails, setShowDetails] = React.useState(false)
  
  const handleSetShowDetails = () => {
    setShowDetails(!showDetails)
  }



  const handleCompleteClick = (id) => {
    setOrderComplete(id).then((response) => {
      if (response === true) {
        dispatch(setOrderStatus(id, 'STATUS_COMPLETED'))
        notify('Success', 'You successfully changed state this order on completed!')
      }
    })
  }

  const handleRejectClick = (id) => {
    setOrderReject(id).then((response) => {
      if (response === true) {
        dispatch(setOrderStatus(id, 'STATUS_REJECTED'))
        notify('Success', 'You successfully changed state this order on rejected!')
      }
    })
  }

  const handleCancelClick = (id) => {
    setOrderCanceled(id).then((response) => {
      if (response === true) {
        dispatch(setOrderStatus(id, 'STATUS_CANCELED'))
        notify('Success', 'You successfully changed state this order on canceled!')
      }
    })
  }

  const handleDeleteClick = (id) => {
    setOrderDelete(id).then((response) => {
      if (response === true) {
        dispatch(deleteOrder(id))
        notify('Success', 'You successfully deleted this order!')
      }
    })
  }
  
  return (
    <form className="userOrder" onSubmit={form.handleSubmit}>
      <div className="userOrder__wrapper">
        <div className="userOrder__header">
          <div className="userOrder__titleAndState">
            {editableOrderData ? (
                <div className="userOrder__editableTitle">
                  <div className="userOrder__fieldContainer">
                    <FormInput name="title" value={values.title} errors={errors.title} onChange={handleChange}/>
                  </div>
                  <p>(Order #{order?.id})</p>
                </div>
            ) : (
                <h3 className="userOrder__title">
                  {order?.title} (Order #{order?.id})
                </h3>
            )}
    
            <Status status={order?.status}/>
          </div>
  
          <div className="userOrder__consumer">
            <p> {order?.consumer.firstName} {order?.consumer.lastName} </p>
            <p> {order?.vendor.firstName !== null &&  order.vendor.lastName !== null ? `${order.vendor.firstName} ${order.vendor.lastName}` : 'No vendor' } </p>
          </div>
        </div>
        
        <div className="userOrder__body">
          <div className="userOrder__details">
            <Detail title="Description" description={order?.description} isEditable={editableOrderData} name="description" value={values.description} error={errors.description} onChange={handleChange}/>
            <Detail title="Address" description={order?.address} isEditable={editableOrderData} name="address" value={values.address} error={errors.address} onChange={handleChange}/>

            {editableOrderData ? (
                <div className="userOrder__editablePrice">
                  <div className="userOrder__fieldContainer">
                    <FormInput name="price" value={values.price} errors={errors.price} onChange={handleChange}/>
                  </div>
                </div>
            ) : (
              <h3 className="userOrder__price">
                {order?.price}
              </h3>
            )}
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
            order.status === 'STATUS_IN_PROGRES' ? (
              <div className="userOrder__footer">
                <button
                    type="button"
                    className="userOrder__complete"
                    onClick={() => handleCompleteClick(order.id)}>
                  Complete
                </button>

                <button
                    type="button"
                    className="userOrder__reject"
                    onClick={() => handleRejectClick(order.id)}>
                  Reject
                </button>
              </div>
            ) : order.status === 'STATUS_OPEN' && (
              <div className="userOrder__footer">
                <button
                    type="button"
                    className="userOrder__reject"
                    onClick={() => handleDeleteClick(order.id)}>
                  Delete
                </button>

                {editableOrderData ? (
                    <button
                        type="input"
                        className="userOrder__complete">
                      Save
                    </button>
                ) : (
                    <div
                        onClick={handleEditOrderData}
                        className="userOrder__editButton">
                      Edit
                    </div>
                )}
              </div>
            )
          )}
  
          {userInfo.role === 'ROLE_SERVICE_PROVIDER' && (
            order.status === 'STATUS_IN_PROGRES' && (
              <div className="userOrder__footer">
                <button
                  type="button"
                  className="userOrder__cancel"
                  onClick={() => handleCancelClick(order.id)}>
                  Cancel
                </button>
              </div>
            )
          )}
      </div>
    </form>
  )
}

export default Order