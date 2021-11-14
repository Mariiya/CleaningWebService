//general
import {createStore} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
//reducers
import rootReducer from "./rootReducer";

const store = createStore(rootReducer, composeWithDevTools())

export default store