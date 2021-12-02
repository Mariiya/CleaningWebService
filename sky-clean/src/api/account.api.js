//methods
import {authPost, get} from "./api";

export const getOrdersByUser = async (id) => {
  return await get(`/order/get-by-user?id=${id}`)
}

export const changePassword = async (data) => {
  return await authPost(`/api/change-password`, data)
}

export const changeUserData = async (scope, data) => {
  return await authPost(`/${scope}/update`, data)
}