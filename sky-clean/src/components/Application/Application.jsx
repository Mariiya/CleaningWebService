//generals
import React from 'react';
import {Route, Switch, BrowserRouter as Router} from "react-router-dom";
//custom hooks
import ScrollToTop from "../../hooks/ScrollToTop";
import PrivateRoute from "../../hooks/PrivateRoute";
//pages
import MainPage from '../../pages/MainPage/MainPage';
import SignInPage from "../../pages/SignInPage/SignInPage";
import SignUpPage from "../../pages/SignUpPage/SignUpPage";
import ResetPasswordPage from "../../pages/ResetPasswordPage/ResetPasswordPage"
import OrdersPage from "../../pages/OrdersPage/OrdersPage";
import NotFound from "../NotFound/NotFound";
//components
import Header from '../Header/Header';
import Main from '../Main/Main';
import Footer from "../Footer/Footer";
//styles
import './Application.scss';

function Application() {
  const isAuth = true
  
  return (
    <Router>
      <div className="application">
        <ScrollToTop/>
        <Header/>
        <Main>
          <Switch>
            <Route exact path="/" component={MainPage}/>
            <Route path="/sign-in" component={SignInPage}/>
            <Route path="/sign-up" component={SignUpPage}/>
            <Route path="/password-reset" component={ResetPasswordPage}/>
            <PrivateRoute path="/orders" children={<OrdersPage/>} isAuth={isAuth}/>
            <Route path="*" component={NotFound}/>
          </Switch>
        </Main>
        <Footer/>
      </div>
    </Router>
  );
}

export default Application;