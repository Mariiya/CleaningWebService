//methods
import {get, authPost} from "./api";

export const getOrder = async (id) => {
  return await get(`/order/${id}`)
}

export const getOrders = async (page = 1) => {
  return await get(`/order/get-by-status?status=STATUS_OPEN&page=${page}`)
}

export const getCountOrders = async () => {
  return await get(`/order/get-number-status?status=STATUS_OPEN`)
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