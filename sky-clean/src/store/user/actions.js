//consts
import {USER} from "./consts";

export const getToken = (accessToken) => {
  return {
    type: USER.GET_ACCESS_TOKEN,
    payload: accessToken
  }
}

export const clearToken = () => {
  return {
    type: USER.CLEAR_ACCESS_TOKEN
  }
}

export const getUserInfo = (user) => {
  return {
    type: USER.GET_USER_INFO,
    payload: user
  }
}

export const clearUserInfo = () => {
  return {
    type: USER.CLEAR_USER_INFO
  }
}