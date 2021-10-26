//general
import React from 'react';
//components
import OrdersFilter from "../../components/OrdersFilter/OrdersFilter";
import Orders from "../../components/Orders/Orders";
//styles
import './OrdersPage.scss'

function OrdersPage() {
    return (
        <div className="ordersPage">
            <OrdersFilter/>
            <Orders/>
        </div>
    )
}

export default OrdersPage;