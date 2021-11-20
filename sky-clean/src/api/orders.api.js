//methods
import {get} from "./api";

export const getOrders = async (page = 1) => {
  return await get(`/order/orders?page=${page}`)
}