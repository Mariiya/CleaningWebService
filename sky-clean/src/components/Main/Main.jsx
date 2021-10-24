//general
import React from 'react';
//styles
import './Main.scss';

function Main(props) {
  React.useEffect(() => {
    fetch('http://localhost:8888/api/auth/signup/vendor', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "vendorId":3635,
        "firstName":"MAsha",
        "lastName":"s",
        "individual": true,
        "user":{
          "id": 9,
          "phoneNumber": "+380963877113",
          "email": "masha2@gmail.com",
          "password":
            "4a46efa4b44b711db8f756ff71b8502bd604d1fc6cc1f7004de38899e3a829df",
          "role": "ROLE_SERVICE_PROVIDER"
        }
      })
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        console.log(data);
      });
  })

  return (
    <main className="main">
      {props.children}
    </main>
  );
};

export  default Main;