//general
import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from "react-redux";
import {ToastContainer} from "react-toastify";
//redux
import store from "./store/store";
//components
import Application from "./components/Application/Application";
//styles
import './styles/index.scss';
import 'react-toastify/dist/ReactToastify.css'



ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <Application/>
      <ToastContainer position={"bottom-right"} />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);
