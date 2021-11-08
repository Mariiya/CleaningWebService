//general
import React from 'react';
//components
import OrdersFilter from "../../components/OrdersFilter/OrdersFilter";
import Orders from "../../components/Orders/Orders";
//styles
import './OrdersPage.scss'

function OrdersPage() {
  // React.useEffect(() => {
  //   document.getElementById('root').scrollTo(0,0)
  // }, []);
  
    return (
        <div className="ordersPage">
            <OrdersFilter/>
            <Orders/>
        </div>
    )
}

export default OrdersPage;