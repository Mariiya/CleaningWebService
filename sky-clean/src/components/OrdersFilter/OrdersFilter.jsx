//general
import React from 'react'
import {useDispatch, useSelector} from "react-redux";
//redux
import {setServices, uncheckAllServices} from "../../store/services/actions";
//api
import {getServices} from "../../api/createOrder.api";
//components
import Service from "./Service/Service";
//styles
import './OrdersFilter.scss'
//assets
import {ReactComponent as SearchIcon} from "../../assets/icons/search.svg";
import {ReactComponent as ArrowDownIcon} from "../../assets/icons/services-arrow-down.svg";
import {ReactComponent as ArrowUpIcon} from "../../assets/icons/services-arrow-down.svg";

const OrdersFilter = () => {
    const dispatch = useDispatch()
    const services = useSelector((state) => state.services.services)
    
    React.useEffect(() => {
        getServices().then((response) => {
            dispatch(setServices(response))
        })
    }, [dispatch])
    
    const [servicesFilterOpen, setServicesFilterOpen] = React.useState(false)
    
    const handleServicesFilterClose = () => {
        setServicesFilterOpen(false)
        dispatch(uncheckAllServices())
    }
    
    const handleServicesFilterOpen = () => {
        setServicesFilterOpen(true)
    }
    
    return (
        <div className="ordersFilter">
            <div className="ordersFilter__wrapper wrapper">
                <div className="ordersFilter__searchInput">
                    <SearchIcon/>
                    <input placeholder="Search order" />
                </div>
                <div className="ordersFilter__prices">
                    <h3 className="ordersFilter__prices-title">Price:</h3>
                    <input className="ordersFilter__price" placeholder="From"/>
                    <input className="ordersFilter__price" placeholder="To"/>
                </div>
                <div className="ordersFilter__filter">
                    <h3 className="ordersFilter__filter-title">Types of work:</h3>
                    {
                        !servicesFilterOpen ?
                          <ArrowDownIcon
                            onClick={handleServicesFilterOpen}/> :
                          <ArrowUpIcon
                            className="ordersFilter__close"
                            onClick={handleServicesFilterClose}/>}
                    {servicesFilterOpen && (
                      <div className="ordersFilter__filterServices">
                          {services?.map((service) => (
                            <Service service={service}/>
                          ))}
                      </div>
                    )}
                </div>
            </div>
        </div>
    )
}

export default OrdersFilter;