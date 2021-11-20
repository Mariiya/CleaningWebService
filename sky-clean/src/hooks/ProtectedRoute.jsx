//general
import React from 'react'
import {Route, Redirect} from 'react-router-dom'

const ProtectedRoute = ({children, isAuth, role, to, ...rest}) => {
  return (
    <Route
      {...rest}
      render={
        ({ location }) => {
          if (isAuth && role === 'ROLE_CLIENT') {
            return (
              children
            )
          } else {
            return (
              <Redirect
                to={{
                  pathname: `${to}`,
                  state: { from: location }
                }}
              />
            )
          }
        }
      }
    />
  )
}

export default ProtectedRoute