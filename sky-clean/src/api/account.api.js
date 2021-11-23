//methods
import {get} from "./api";

export const getOrdersByUser = async (id) => {
  return await get(`/order/get-by-user?id=${id}`)
}