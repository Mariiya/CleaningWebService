//general
import React from 'react';
import {useParams} from "react-router";
//components
import Order from "../../components/Order/Order";
//styles
import './OrderPage.scss'

function OrderPage() {
  let {id} = useParams()
  
  return (
    <div className="orderPage">
      <Order id ={id}/>
    </div>
  )
}

export default OrderPage;