//general
import React from 'react'
import {Route, Redirect} from 'react-router-dom'
import {useSelector} from "react-redux";

const ProtectedRoute = ({component: Component, ...rest}) => {
    const accessToken = useSelector((state) => state.user.accessToken)
    const userInfo = useSelector((state) => state.user.userInfo)
    const userRole = useSelector((state) => state.user.userInfo?.role)
    const isAuth = !!accessToken && !!userInfo

    return (
        <Route {...rest}
           render={(props) => (
               isAuth && userRole === 'ROLE_CLIENT' ? <Component {...props}/> : <Redirect to="/"/>
           )}
        />
    )
}

export default ProtectedRoute