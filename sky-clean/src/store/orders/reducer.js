//consts
import {ORDERS} from "./consts";

const initialState = {
  orders: [],
  filters: {
    title: '',
    minPrice: 0,
    maxPrice: 0,
    service: 0,
  }
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
        orders: [],
        filters: {
          title: '',
          minPrice: 0,
          maxPrice: 0,
          service: 0,
        }
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

    case ORDERS.SET_FILTERS: {
      return {
        ...state,
        filters: {...state.filters, ...action.payload}
      }
    }
    
    default:
      return state
  }
}

export default ordersReducer