//general
import React from 'react'
//components
//styles
import './OrdersFilter.scss'
import {ReactComponent as SearchIcon} from "../../assets/icons/search.svg";
import {ReactComponent as ArrowIcon} from "../../assets/icons/services-arrow-down.svg";

const OrdersFilter = () => {
    return (
        <div className="ordersFilter">
            <div className="ordersFilter__wrapper wrapper">
                <div className="ordersFilter__searchInput">
                    <SearchIcon/>
                    <input placeholder="Search order" />
                </div>
                <div className="ordersFilter__prices">
                    <h3 className="ordersFilter__prices-title">Price:</h3>
                    <input className="ordersFilter__price" placeholder="From"/>
                    <input className="ordersFilter__price" placeholder="To"/>
                </div>
                <div className="ordersFilter__filter">
                    <h3 className="ordersFilter__filter-title">Types of work:</h3>
                    <ArrowIcon/>
                </div>
            </div>
        </div>
    )
}

export default OrdersFilter;