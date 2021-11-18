//general
import React from 'react';
import {NavLink} from "react-router-dom";
//redux
import {useDispatch, useSelector} from "react-redux";
import {clearToken, clearUserInfo} from "../../store/user/actions";
//styles
import './Header.scss';
//assets
import {ReactComponent as Logo} from "../../assets/icons/logo.svg";
import {ReactComponent as SingUpBtnIcon} from "../../assets/icons/sign-up-btn.svg";
import {ReactComponent as SingInBtnIcon} from "../../assets/icons/sign-in-btn.svg";
import {ReactComponent as SignOutBtnIcon} from "../../assets/icons/sing-out-btn.svg";
import {ReactComponent as UserAccountIcon} from "../../assets/icons/user.svg";
import {ReactComponent as MobileMenu} from "../../assets/icons/mobile-menu.svg";
import {ReactComponent as CloseMobileMenu} from "../../assets/icons/close-menu.svg";
import {ReactComponent as CreateOrderIcon} from "../../assets/icons/create-order.svg";

function Header() {
  const dispatch = useDispatch()
  
  const [openMenu, setOpenMenu] = React.useState(false);
  const accessToken = useSelector((state) => state.user.accessToken)
  const userInfo = useSelector((state) => state.user.userInfo)
  const userRole = useSelector((state) => state.user.userInfo?.role)
  const isAuth = !!accessToken && !!userInfo
  
  const handleMenuClick = () => {
    setOpenMenu(!openMenu);
  }
  
  const handleSignOutClick = () => {
    dispatch(clearToken())
    dispatch(clearUserInfo())
  }
  
  return (
    <header className="header">
      <div className="header__wrapper wrapper">
        <NavLink className="header__logo-container" to="/">
          <div className="header__logo">
            <Logo className="header__logo-icon"/>
          </div>
          <h1 className="header__logo-title">SkyClean</h1>
        </NavLink>
        
        <nav className="header__navigation">
          <div className="header__main">
            <NavLink className="header__main-link" to="/">Main</NavLink>
            <NavLink className="header__main-link" to="/orders">Orders</NavLink>
          </div>
          
          <div className="header__controls">
            {!isAuth ? (
              <>
                <NavLink className="header__control-link" to="/sign-in">
                  Sign in
                  <div className="header__image-container">
                    <SingInBtnIcon/>
                  </div>
                </NavLink>
                <NavLink className="header__control-link" to="/sign-up">
                  Sign up
                  <div className="header__image-container">
                    <SingUpBtnIcon/>
                  </div>
                </NavLink>
              </>
            ) : (
              <>
                {userRole === 'ROLE_CLIENT' && (
                  <NavLink className="header__control-link" to="/create-order">
                    Create
                    <div className="header__image-container">
                      <CreateOrderIcon/>
                    </div>
                  </NavLink>
                )}
                <button className="header__control-link" onClick={handleSignOutClick}>
                  Sign out
                  <div className="header__image-container">
                    <SignOutBtnIcon/>
                  </div>
                </button>
                <NavLink className="header__control-link" to="/account">
                  Account
                  <div className="header__image-container">
                    <UserAccountIcon/>
                  </div>
                </NavLink>
              </>
            )}
          </div>
        </nav>
        
        <button className="header__mobile-btn" onClick={handleMenuClick}>
          {
            !openMenu ?
              <MobileMenu className="header__open-btn"/> :
              <CloseMobileMenu className="header__close-btn"/>
          }
        </button>
        
        <nav className={
          !openMenu ?
            "header__mobile-navigation" :
            "header__mobile-navigation header__mobile-navigation_open "
        }>
          <div className="header__mobile-wrapper wrapper">
            <NavLink className="header__main-link" to="/" onClick={handleMenuClick}>Main</NavLink>
            <NavLink className="header__main-link" to="/orders" onClick={handleMenuClick}>Orders</NavLink>
            {!isAuth ? (
              <>
                <NavLink className="header__main-link" to="/sign-in" onClick={handleMenuClick}>Sign in</NavLink>
                <NavLink className="header__main-link" to="/sign-up" onClick={handleMenuClick}>Sign up</NavLink>
              </>
            ) : (
              <>
                {userRole === "ROLE_CLIENT" && (<NavLink className="header__main-link" to="/create-order" onClick={handleMenuClick}>Create order</NavLink>)}
                <button className="header__main-link" onClick={() => {handleMenuClick(); handleSignOutClick()}}>Sign out</button>
                <NavLink className="header__main-link" to="/account" onClick={handleMenuClick}>Account</NavLink>
              </>
            )}
          </div>
        </nav>
      </div>
    </header>
  )
}

export default Header;