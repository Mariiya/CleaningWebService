//const
import {ORDERS} from "./consts";

export const setOrders = (orders) => {
  return {
    type: ORDERS.SET_ORDERS,
    payload: orders
  }
}