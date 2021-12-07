//general
import React from 'react'
import {useDispatch, useSelector} from "react-redux";
//redux
import {setServices, uncheckAllServices} from "../../store/services/actions";
//api
import {getServices} from "../../api/createOrder.api";
//ui
import FormInput from "../../UI/FormInput/FormInput";
//components
import Service from "./Service/Service";
//styles
import './OrdersFilter.scss'
//assets
import {ReactComponent as SearchIcon} from "../../assets/icons/search.svg";
import {ReactComponent as ArrowDownIcon} from "../../assets/icons/services-arrow-down.svg";
import {ReactComponent as ArrowUpIcon} from "../../assets/icons/services-arrow-down.svg";

const OrdersFilter = ({values, errors, handleChange, form, setSelectedService}) => {
    const dispatch = useDispatch()
    const services = useSelector((state) => state.services.services)
    
    React.useEffect(() => {
        getServices().then((response) => {
            dispatch(setServices(response))
        })

        return () => {
            dispatch(uncheckAllServices())
        }
    }, [dispatch])
    
    const [servicesFilterOpen, setServicesFilterOpen] = React.useState(false)

    const handleServicesFilterClose = () => {
        setServicesFilterOpen(false)
    }
    
    const handleServicesFilterOpen = () => {
        setServicesFilterOpen(true)
    }

    const handleClearFilterClick = () => {
        form.resetForm()
        setSelectedService(0)
        dispatch(uncheckAllServices())
    }

    const handleSearchClick = (event) => {
        event.preventDefault()
        form.handleSubmit(event)
        setServicesFilterOpen(false)
    }
    
    return (
        <form className="ordersFilter" onSubmit={handleSearchClick}>
            <div className="ordersFilter__wrapper wrapper">
                <div className="ordersFilter__filters">
                    <div className="ordersFilter__searchInput">
                        <SearchIcon/>
                        <input name='title' value={values.title} onChange={handleChange} placeholder="Search order" />
                    </div>
                    <div className="ordersFilter__prices">
                        <h3 className="ordersFilter__prices-title">Price:</h3>
                        <div className="ordersFilter__price">
                            <FormInput placeholder="From" name='minPrice' value={values.minPrice} onChange={handleChange} errors={errors.minPrice}/>
                        </div>
                        <div className="ordersFilter__price">
                            <FormInput placeholder="To" name='maxPrice' value={values.maxPrice} onChange={handleChange} errors={errors.maxPrice}/>
                        </div>
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
                                    <Service key={service.name} service={service} setSelectedService={setSelectedService}/>
                                ))}
                            </div>
                        )}
                    </div>
                </div>
                <div className="ordersFilter__btns">
                    <button className="ordersFilter__searchBtn" type="submit">
                        Search
                    </button>

                    <div className="ordersFilter__searchBtn" onClick={handleClearFilterClick}>
                        Clear
                    </div>
                </div>
            </div>
        </form>
    )
}

export default OrdersFilter;