//methods
import {get, authPost} from "./api";

export const getServices = async () => {
  return await get('/service/services/')
}

export const createNewOrder = async (newOrder) => {
  return await authPost('/order/create/', newOrder)
}

export const createCustomService = async (customService) => {
  return await authPost('/service/custom/', customService)
}