//general
import React from 'react'
import {Route, Redirect} from 'react-router-dom'

const ProtectedRoute = ({children, isAuth, role, to, ...rest}) => {
  return (
    <Route
      {...rest}
      render={
        ({ location }) => (
          isAuth || role === 'ROLE_CLIENT'
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

export default ProtectedRoute