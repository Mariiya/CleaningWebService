//general
import React from 'react';
import { NavLink} from "react-router-dom";
//styles
import './Header.scss';
//assets
import {ReactComponent as Logo} from "../../assets/icons/logo.svg";
import {ReactComponent as SingUpBtnIcon} from "../../assets/icons/sign-up-btn.svg";
import {ReactComponent as SingInBtnIcon} from "../../assets/icons/sign-in-btn.svg";
import {ReactComponent as MobileMenu} from "../../assets/icons/mobile-menu.svg";
import {ReactComponent as CloseMobileMenu} from "../../assets/icons/close-menu.svg";

function Header() {
  const [openMenu, setOpenMenu] = React.useState(false);

  const handleMenuClick = () => {
    setOpenMenu(!openMenu);
  }

  return (
    <header className="header">
      <div className="header__wrapper wrapper">
        <div className="header__logo-container">
          <div className="header__logo">
            <Logo className="header__logo-icon"/>
          </div>
          <h1 className="header__logo-title">SkyClean</h1>
        </div>

        <nav className="header__navigation">
          <div className="header__main">
            <NavLink className="header__main-link" to="/">Main</NavLink>
            <NavLink className="header__main-link" to="/orders">Orders</NavLink>
          </div>

          <div className="header__controls">
            <NavLink className="header__control-link" to="/sign-in">
              Sign in
              <SingInBtnIcon/>
            </NavLink>
            <NavLink className="header__control-link" to="/sign-up">
              Sign up
              <SingUpBtnIcon/>
            </NavLink>
          </div>
        </nav>

        <button className="header__mobile-btn" onClick={() => handleMenuClick()}>
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
            <NavLink className="header__main-link" to="/">Main</NavLink>
            <NavLink className="header__main-link" to="/orders">Orders</NavLink>
            <NavLink className="header__main-link" to="/sign-in">Sign in</NavLink>
            <NavLink className="header__main-link" to="/sign-up">Sign up</NavLink>
          </div>
        </nav>
      </div>
    </header>
  )
}

export default Header;