//general
import {combineReducers} from "redux";
//reducers
import userReducer from "./user/reducer";
import servicesReducer from "./services/reducer";
import ordersReducer from "./orders/reducer";

const rootReducer = combineReducers({
  user: userReducer,
  services: servicesReducer,
  orders: ordersReducer,
})

export default rootReducer