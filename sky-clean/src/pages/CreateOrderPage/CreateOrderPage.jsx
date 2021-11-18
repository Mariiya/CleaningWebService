//general
import React from 'react'
//components
import CreateOrderFormEditor from "../../components/CreateOrder/CreateOrderFormEditor";
//styles
import './CreateOrderPage.scss'

function CreateOrderPage() {
  return (
    <div className="createOrderPage">
      <CreateOrderFormEditor/>
    </div>
  )
}

export default CreateOrderPage