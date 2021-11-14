//general
import React from 'react'
import {Route, Redirect} from 'react-router-dom'

const PrivateRoute = ({children, isAuth, to, ...rest}) => {
  return (
    <Route
      {...rest}
      render={
        ({ location }) => (
          isAuth
            ? (
              children
            ) : (
              <Redirect
                to={{
                  pathname: `${to}`,
                  state: { from: location }
                }}
              />
            ))
      }
    />
  )
}

export default PrivateRoute