//consts
import {USER} from "./consts";

const initialState = {
  accessToken: null,
  userInfo: null
}

function userReducer(state = initialState, action ) {
  switch (action.type) {
    case USER.GET_ACCESS_TOKEN: {
      const token = JSON.stringify(action.payload)
      localStorage.setItem('access_token', token)
      return {
        ...state,
        accessToken: action.payload
      }
    }
    
    case USER.CLEAR_ACCESS_TOKEN: {
      localStorage.clear()
      return {
        ...state,
        accessToken: null
      }
    }
    
    case USER.GET_USER_INFO: {
      const userInfo = JSON.stringify(action.payload)
      localStorage.setItem('user_info', userInfo)
      return {
        ...state,
        userInfo: action.payload
      }
    }

    case USER.UPDATE_USER_INFO: {
      const userInfo = JSON.stringify(action.payload)
      localStorage.setItem('user_info', userInfo)
      return {
        ...state,
        userInfo: action.payload
      }
    }
    
    case USER.CLEAR_USER_INFO: {
      localStorage.clear()
      return {
        ...state,
        userInfo: null
      }
    }
    
    default:
      return state
  }
}

export default userReducer