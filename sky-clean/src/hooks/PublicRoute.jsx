//general
import React from 'react'
import {Route, Redirect} from 'react-router-dom'
import {useSelector} from "react-redux";

const PublicRoute = ({component: Component, restricted, ...rest}) => {
    const accessToken = useSelector((state) => state.user.accessToken)
    const userInfo = useSelector((state) => state.user.userInfo)
    const isAuth = !!accessToken && !!userInfo

    return (
        <Route {...rest}
            render={(props) => (
                isAuth && restricted ? <Redirect to="/"/> : <Component {...props}/>
            )}
        />
    )
}

export default PublicRoute