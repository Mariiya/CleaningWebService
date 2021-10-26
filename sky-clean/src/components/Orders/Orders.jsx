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
            {id: 2, title: 'Eco clean'}],
            price: 2000},
        {id: 2, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'},
                {id: 4, title: 'Eco clean'},],
            price: 2000},
        {id: 3, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'}],
            price: 2000},
        {id: 4, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'}],
            price: 2000},
        {id: 5, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'}],
            price: 2000},
        {id: 6, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'}],
            price: 2000},
        {id: 7, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'}],
            price: 2000},
        {id: 8, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'}],
            price: 2000},
        {id: 9, title: "Clean room", consumer: 'Ivan Ivanov',
            services: [
                {id: 1, title: 'Eco clean'},
                {id: 2, title: 'Eco clean'},
                {id: 3, title: 'Eco clean'}],
            price: 2000}]

    const count = 15

    const getPages = count => {
        let content = [];
        for (let i = 1; i <= count; i++) {
            const item = i;
            item < 4 && content.push(<div key={i}>{item}</div>)
        }
        content[content.length - 1].key !== String(count) && content.push(<div key={count + 1}>...</div>, <div className="orders__page" key={count + 2}>{count}</div>)
        return content;
    };

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

                <div className="orders__pagination">
                    {getPages(count)}
                </div>
            </div>
        </div>
    )
}

export default Orders