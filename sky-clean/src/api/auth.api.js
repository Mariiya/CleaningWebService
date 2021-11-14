import {post} from "./api";

export const getAccessToken = async (data) => {
  return await post('/api/auth/signin', data)
}

export const addNewUser = async (data, scope) => {
  return await post(`/api/auth/signup/${scope}`, data)
}