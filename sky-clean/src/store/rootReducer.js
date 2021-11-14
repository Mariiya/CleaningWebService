//general
import {combineReducers} from "redux";
//reducers
import userReducer from "./user/reducer";

const rootReducer = combineReducers({
  user: userReducer
})

export default rootReducer