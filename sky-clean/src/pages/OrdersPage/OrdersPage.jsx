//general
import React from 'react';
//components
import OrdersFilterEditor from "../../components/OrdersFilter/OrdersFilterEditor";
import Orders from "../../components/Orders/Orders";
//styles
import './OrdersPage.scss'

function OrdersPage() {
    return (
        <div className="ordersPage">
            <OrdersFilterEditor/>
            <Orders/>
        </div>
    )
}

export default OrdersPage;