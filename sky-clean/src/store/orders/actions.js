//const
import {ORDERS} from "./consts";

export const setOrders = (orders) => {
  return {
    type: ORDERS.SET_ORDERS,
    payload: orders
  }
}

export const setOrderStatus = (id, status) => {
  return {
    type: ORDERS.SET_ORDER_STATUS,
    payload: {
      id,
      status
    }
  }
}

export const deleteOrder = (id) => {
  return {
    type: ORDERS.DELETE_ORDER,
    payload: id
  }
}

export const clearOrders = () => {
  return {
    type: ORDERS.CLEAR_ORDERS,
  }
}
