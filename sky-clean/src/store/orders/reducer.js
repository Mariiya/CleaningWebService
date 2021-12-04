//consts
import {ORDERS} from "./consts";

const initialState = {
  orders: []
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

    case ORDERS.SET_ORDER_STATUS:
      return {
        ...state,
        orders: state.orders.map((order) => {
          if (order.id === action.payload.id) {
            return {...order, status: action.payload.status}
          } else {
            return order
          }
        })
      }

    case ORDERS.DELETE_ORDER:
      return {
        ...state,
        orders: state.orders.filter((order) => order.id !== action.payload)
      }

    case ORDERS.CLEAR_ORDERS:
      return {
        ...state,
        orders: []
      }

    case ORDERS.UPDATE_ORDER: {
      return {
        ...state,
        orders: state.orders.map((order) => {
          if (order.id === action.payload.id) {
            return {...order, ...action.payload.data}
          } else {
            return order
          }
        })
      }
    }
    
    default:
      return state
  }
}

export default ordersReducer