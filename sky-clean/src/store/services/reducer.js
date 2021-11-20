//consts
import {SERVICES} from "./consts";

const initialState = {
  services: null
}

function servicesReducer(state = initialState, action) {
  switch (action.type) {
    case SERVICES.SET_SERVICES:
      return {
        ...state,
        services: action.payload.map((service) => ({
          ...service,
          state: false
        }))
      }
      
    case SERVICES.SET_SERVICE_CHECK:
      return {
        ...state,
        services: state.services.map((service) => {
          if (service.id === action.payload) {
            return {...service, state: !service.state}
          } else {
            return service
          }
        })
      }
      
    case SERVICES.ADD_CUSTOM_SERVICE:
      const customService = action.payload
      customService.state = true
      
      return {
        ...state,
        services: [...state.services, customService]
      }
      
    case SERVICES.CLEAR_CUSTOM_SERVICES:
      return {
        ...state,
        services: state.services.filter((service) => service.custom === false)
      }
    
    default:
      return state
  }
}

export default servicesReducer