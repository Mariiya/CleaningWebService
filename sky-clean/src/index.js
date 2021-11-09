//general
import React from 'react';
import ReactDOM from 'react-dom';
//components
import Application from "./components/Application/Application";
//styles
import './styles/index.scss';
import AuthContextProvider from "./helpers/AuthContextProvider";

ReactDOM.render(
  <React.StrictMode>
    <AuthContextProvider>
      <Application />
    </AuthContextProvider>
  </React.StrictMode>,
  document.getElementById('root')
);
