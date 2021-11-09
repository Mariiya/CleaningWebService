//general
import React from 'react'

export const AuthContext = React.createContext()

const AuthContextProvider = ({children}) => {
  const [authUser, setAuthUser] = React.useState(null)

  React.useEffect(() => {
    setAuthUser(localStorage.getItem('accessToken'))
  }, [])
  
  return (
    <AuthContext.Provider value={{authUser}}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthContextProvider