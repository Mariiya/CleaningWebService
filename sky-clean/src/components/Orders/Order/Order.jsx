//general
import React from 'react'
import {Link} from "react-router-dom";
//styles
import './Order.scss'
//assets
import {ReactComponent as CheckBoxIcon} from "../../../assets/icons/sign-up-check-box.svg";
import {ReactComponent as ArrowIcon} from "../../../assets/icons/order-arrow-right.svg";

const Order = ({order}) => {
    return (
        <div className="order">
            <div className="order__header">
                <h3 className="order__title">{order.title}</h3>
                <h3 className="order__consumer">{order.consumer.firstName} {order.consumer.lastName}</h3>
            </div>

            <div className="order__body">
                <div className="order__services">
                    {order.services && order.services.map((service, index) => (
                        index > 2 ? null : <div key={service.id} className="order__checkBox">
                            <div className="order__checkBox-icon">
                                <CheckBoxIcon/>
                            </div>
                            <p>{service.name}</p>
                        </div>
                    ))}
                </div>

                <h3 className="order__price">{order.price}</h3>
            </div>

            <div className="order__footer">
                <Link className="order__moreLink" to={`/order/${order.id}`}>View more <ArrowIcon/></Link>
            </div>
        </div>
    )
}

export default Order