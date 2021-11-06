//general
import React from 'react'
//components
import Order from "./Order/Order";
//styles
import './Orders.scss'

const Orders = () => {
    const orders = [
        {id: 1, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
            {id: 1, title: 'Eco clean'},
            {id: 2, title: 'Cleaning of apartments'}],
            price: 3000},
        {id: 2, title: "Clean room", consumer: 'Andrey Andreev',
            services: [
                {id: 1, title: 'Spring-cleaning'},
                {id: 2, title: 'Cleaning after renovation'},
                {id: 3, title: 'Dry cleaning'},
                {id: 4, title: 'Cleaning of cottages'},],
            price: 4000},
        {id: 3, title: "Cleaning of cottage", consumer: 'Evgeniy Petrov',
            services: [
                {id: 1, title: 'Washing windows'},
                {id: 2, title: 'Spring-cleaning'},
                {id: 3, title: 'Dry cleaning'}],
            price: 5000},
        {id: 4, title: "Clean facade", consumer: 'Michail Michailov',
            services: [
                {id: 1, title: 'Facade washing'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Spring-cleaning'}],
            price: 1500},]

    return (
        <div className="orders">
            <div className="orders__wrapper wrapper">
                <h2 className="orders__title">
                    Orders
                </h2>

                <div className="orders__list">
                    {orders && orders.map((order) => (
                        <Order key={order.id} order={order}/>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default Orders