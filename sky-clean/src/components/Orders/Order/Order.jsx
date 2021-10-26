//general
import React from 'react'
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
                <h3 className="order__consumer">{order.consumer}</h3>
            </div>

            <div className="order__body">
                <div className="order__services">
                    {order.services && order.services.map((service, index) => (
                        index > 2 ? null : <div key={service.id} className="order__checkBox">
                            <div className="order__checkBox-icon">
                                <CheckBoxIcon/>
                            </div>
                            <p>{service.title}</p>
                        </div>
                    ))}
                </div>

                <h3 className="order__price">{order.price}</h3>
            </div>

            <div className="order__footer">
                <a className="order__moreLink" href="/#">View more <ArrowIcon/></a>
            </div>
        </div>
    )
}

export default Order