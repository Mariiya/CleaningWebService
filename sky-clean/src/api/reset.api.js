import {post} from "./api";

export const sendMailForResetPassword = async (email) => {
  return await post(`/api/auth/reset-password?email=${email}`)
}

export const sendCodeForResetPassword = async (email) => {
  return await post(`/api/auth/new-password?email=${email}`)
}