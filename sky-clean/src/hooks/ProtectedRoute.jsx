//general
import React from 'react'
import {Route, Redirect} from 'react-router-dom'

const ProtectedRoute = ({children, isAuth, ...rest}) => {
  return (
    <Route
      {...rest}
      render={
        ({ location }) => (
          !isAuth
            ? (
              children
            ) : (
              <Redirect
                to={{
                  pathname: '/',
                  state: { from: location }
                }}
              />
            ))
      }
    />
  )
}

export default ProtectedRoute