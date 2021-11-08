//general
import React from 'react';
import ReactDOM from 'react-dom';
//components
import Application from "./components/Application/Application";
//styles
import './styles/index.scss';

ReactDOM.render(
  <React.StrictMode>
    <Application />
  </React.StrictMode>,
  document.getElementById('root')
);
