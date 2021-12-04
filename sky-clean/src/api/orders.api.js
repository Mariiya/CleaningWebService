//methods
import {get, authPost} from "./api";

export const getOrder = async (id) => {
  return await get(`/order/${id}`)
}

export const getOrders = async (page = 1, minPice= 0, maxPrice = 0, title = '', serviceId = 0) => {
  return await get(`/order/get-by-multiparams?minPrice=${minPice}&maxPrice=${maxPrice}&title=${title}&status=EMPTY&serviceId=${serviceId}&page=${page}`)
}

export const getCountOrders = async (minPice= 0, maxPrice = 0, title = '', serviceId = 0) => {
  return await get(`/order/get-number-multiparam?minPrice=${minPice}&maxPrice=${maxPrice}&title=${title}&status=EMPTY&serviceId=${serviceId}`)
}

export const assignOrder = async (userId, orderId) => {
  return await authPost(`/order/assignOrder?orderId=${orderId}&vendorId=${userId}`)
}

export const setOrderInProgress = async (orderId) => {
  return await authPost(`/order/inProgress/${orderId}`)
}

export const setOrderComplete = async (orderId) => {
  return await authPost(`/order/complete/${orderId}`)
}

export const setOrderReject = async (orderId) => {
  return await authPost(`/order/reject/${orderId}`)
}

export const setOrderCanceled = async (orderId) => {
  return await authPost(`/order/cancel/${orderId}`)
}

export const setOrderDelete = async (orderId) => {
  return await authPost(`/order/delete/${orderId}`)
}

export const updateOrder = async (order) => {
  return await authPost(`/order/update/`, order)
}