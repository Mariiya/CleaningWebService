//methods
import {get} from "./api";

export const getServices = async () => {
  return await get('/service/services')
}