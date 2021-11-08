//general
import React from 'react'
import {useLocation} from "react-router";

const ScrollToTop = () => {
  const location = useLocation()
  
  React.useEffect(() => {
    document.getElementById('root').scrollTo(0, 0)
  }, [location.pathname])
  
  return null
}

export default ScrollToTop