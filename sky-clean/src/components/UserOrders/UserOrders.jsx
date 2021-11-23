//general
import React from 'react'
import {useDispatch, useSelector} from "react-redux";
//api
import {getOrdersByUser} from "../../api/account.api";
//redux
import {setOrders} from "../../store/orders/actions";
//ui
import Spinner from "../../UI/Spinner/Spinner";
//components
import Order from "./Order/Order";
//styles
import './UserOrders.scss'

function UserOrders() {
  const dispatch = useDispatch()
  const orders = useSelector((state) => state.orders.orders)
  const userInfo = useSelector((state) => state.user.userInfo)
  
  const [loading, setLoading] = React.useState(false)
  
  React.useEffect(() => {
    setLoading(true)
    getOrdersByUser(userInfo.id).then((response) => {
      setLoading(false)
      dispatch(setOrders(response))
    })
  }, [dispatch, userInfo.id])
  
  return (
    <div className="userOrders">
      {loading ? (
        <div className="userOrders__spinnerContainer">
          <Spinner/>
        </div>
      ) : orders?.map((order) => (
        <Order key={order.id} order={order}/>
      ))}
    </div>
  )
}

export default UserOrders