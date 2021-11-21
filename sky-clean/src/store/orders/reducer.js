//consts
import {ORDERS} from "./consts";

const initialState = {
  orders: null
}

function ordersReducer(state = initialState, action) {
  switch (action.type) {
    case ORDERS.SET_ORDERS:
      return {
        ...state,
        orders: action.payload.sort((a, b) => {
          return b.id - a.id
        })
      }
    
    default:
      return state
  }
}

export default ordersReducer