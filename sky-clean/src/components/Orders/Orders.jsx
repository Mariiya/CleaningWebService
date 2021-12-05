//general
import React from 'react'
import {useDispatch, useSelector} from "react-redux";
//api
import {getCountOrders, getOrders} from "../../api/orders.api";
//redux
import {setOrders} from "../../store/orders/actions";
//ui
import Spinner from "../../UI/Spinner/Spinner";
//components
import Order from "./Order/Order";
//styles
import './Orders.scss'

const Orders = () => {
    const dispatch = useDispatch()
    const orders = useSelector((state) => state.orders.orders)
    const filters = useSelector((state) => state.orders.filters)
    
    const [loading, setLoading] = React.useState(false)
    const [countPages, setCountPages] = React.useState(null)
    const [currentPage, setCurrentPage] = React.useState(1)
    const pages = []

    for (let i = 1; i < countPages + 1; i++) {
        pages.push(i)
    }
    
    React.useEffect(() => {
        setLoading(true)
        const orders = getOrders(currentPage, filters.minPrice, filters.maxPrice, filters.title, filters.service).then((response) => {
            dispatch(setOrders(response))
        })
        const ordersCount = getCountOrders(filters.minPrice, filters.maxPrice, filters.title, filters.service).then((response) => {
            const countPages = Math.ceil(response / 15)
            setCountPages(countPages)
        })

        Promise.all([orders, ordersCount]).finally(() => {
            setLoading(false)
        })
    }, [dispatch, currentPage, filters.minPrice, filters.maxPrice, filters.title, filters.service])
    
    const handleChangeCurrentPage = (numberPage) => {
        setCurrentPage(numberPage)
    }
    
    return (
        <div className="orders">
            <div className="orders__wrapper wrapper">
                <h2 className="orders__title">
                    Orders
                </h2>
    
                {loading ? (
                  <div className="spinnerForOrders">
                    <Spinner/>
                  </div>
                ) :
                    orders?.length ? (
                        <div className="orders__list">
                            {orders.map((order) => (
                                <Order key={order.id} order={order}/>
                            ))}
                        </div>
                    ) : (<p className="orders__noResult">No results</p>)
                }
    
                <div className="orders__pagination">
                    {pages?.map((page) => (
                      <div
                        key={page}
                        className={currentPage === page ? "orders__page orders__page_active" : "orders__page"}
                        onClick={() => handleChangeCurrentPage(page)}>
                          {page}
                      </div>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default Orders