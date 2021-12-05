//general
import React from 'react'
import {useDispatch, useSelector} from "react-redux";
//api
import {getOrdersByUser} from "../../api/account.api";
//redux
import {clearOrders, setOrders} from "../../store/orders/actions";
//ui
import Spinner from "../../UI/Spinner/Spinner";
//components
import OrderFormEditor from "./Order/OrderFormEditor";
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
      dispatch(setOrders(response))
    })
    .finally(() => {
      setLoading(false)
    })

    return () => {
      dispatch(clearOrders())
    }
  }, [dispatch, userInfo.id])
  
  return (
    <div className="userOrders">
      {loading ? (
        <div className="userOrders__spinnerContainer">
          <Spinner/>
        </div>
      ) : orders?.length ? orders?.map((order) => (
          <OrderFormEditor key={order.id} order={order}/>
      )) : (<p className="userOrders__noResult">No results</p>)}
    </div>
  )
}

export default UserOrders