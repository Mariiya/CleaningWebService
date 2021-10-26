//general
import React from 'react';
//style
import './Main.scss';

function Main(props) {
  return (
    <main className="main">
      {props.children}
    </main>
  );
};

export  default Main;