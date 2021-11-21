//methods
import {get} from "./api";

export const getOrder = async (id) => {
  return await get(`/order/${id}`)
}

export const getOrders = async (page = 1) => {
  return await get(`/order/get-by-status?status=STATUS_OPEN&page=${page}`)
}

export const getCountOrders = async () => {
  return await get(`/order/get-number-status?status=STATUS_OPEN`)
}