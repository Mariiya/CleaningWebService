//consts
import {SERVICES} from "./consts";

export const setServices = (services) => {
  return {
    type: SERVICES.SET_SERVICES,
    payload: services
  }
}

export const setServiceCheck = (id) => {
  return {
    type: SERVICES.SET_SERVICE_CHECK,
    payload: id
  }
}

export const addCustomService = (customService) => {
  return {
    type: SERVICES.ADD_CUSTOM_SERVICE,
    payload: customService
  }
}

export const clearCustomServices = () => {
  return {
    type: SERVICES.CLEAR_CUSTOM_SERVICES,
  }
}

export const setOneServiceCheck = (id) => {
  return {
    type: SERVICES.SET_ONE_SERVICE_CHECK,
    payload: id
  }
}

export const uncheckAllServices = () => {
  return {
    type: SERVICES.UNCHECK_ALL_SERVICES
  }
}