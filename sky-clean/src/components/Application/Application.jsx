//generals
import React from 'react';
import {Route, Switch, BrowserRouter as Router} from "react-router-dom";
//custom hooks
import ScrollToTop from "../../hooks/ScrollToTop";
import PrivateRoute from "../../hooks/PrivateRoute";
import ProtectedRoute from "../../hooks/ProtectedRoute";
//context
import {AuthContext} from "../../helpers/AuthContextProvider";
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
  const {authUser: accessToken} = React.useContext(AuthContext)
  
  return (
    <Router>
      <div className="application">
        <ScrollToTop/>
        <Header/>
        <Main>
          <Switch>
            <Route exact path="/" component={MainPage}/>
            <ProtectedRoute path="/sign-in" children={<SignInPage/>} isAuth={accessToken}/>
            <ProtectedRoute path="/sign-up" children={<SignUpPage/>} isAuth={accessToken}/>
            <ProtectedRoute path="/password-reset" children={<ResetPasswordPage/>} isAuth={accessToken}/>
            <PrivateRoute path="/orders" children={<OrdersPage/>} isAuth={accessToken}/>
            <Route path="*" component={NotFound}/>
          </Switch>
        </Main>
        <Footer/>
      </div>
    </Router>
  );
}

export default Application;