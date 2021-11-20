//general
import {combineReducers} from "redux";
//reducers
import userReducer from "./user/reducer";
import servicesReducer from "./services/reducer";

const rootReducer = combineReducers({
  user: userReducer,
  services: servicesReducer
})

export default rootReducer