//general
import React from 'react'
import {Route, Redirect} from 'react-router-dom'
import {useSelector} from "react-redux";

const PrivateRoute = ({component: Component, ...rest}) => {
    const accessToken = useSelector((state) => state.user.accessToken)
    const userInfo = useSelector((state) => state.user.userInfo)
    const isAuth = !!accessToken && !!userInfo

    return (
        <Route {...rest}
           render={(props) => (
               isAuth ? <Component {...props}/> : <Redirect to="/sign-in"/>
           )}
        />
    )
}

export default PrivateRoute