//generals
import React from 'react';
import {Route, Switch, BrowserRouter as Router} from "react-router-dom";
import {useDispatch} from "react-redux";
//redux
import {getToken, getUserInfo} from "../../store/user/actions";
//custom hooks
import ScrollToTop from "../../hooks/ScrollToTop";
import PublicRoute from "../../hooks/PublicRoute";
import PrivateRoute from "../../hooks/PrivateRoute";
//pages
import MainPage from '../../pages/MainPage/MainPage';
import SignInPage from "../../pages/SignInPage/SignInPage";
import SignUpPage from "../../pages/SignUpPage/SignUpPage";
import ResetPasswordPage from "../../pages/ResetPasswordPage/ResetPasswordPage"
import OrdersPage from "../../pages/OrdersPage/OrdersPage";
import CreateOrderPage from "../../pages/CreateOrderPage/CreateOrderPage";
import AccountPage from "../../pages/AccountPage/AccountPage";
import OrderPage from "../../pages/OrderPage/OrderPage";
import NotFound from "../NotFound/NotFound";
//components
import Header from '../Header/Header';
import Main from '../Main/Main';
import Footer from "../Footer/Footer";
//styles
import './Application.scss';
import ProtectedRoute from "../../hooks/ProtectedRoute";

function Application() {
  const dispatch = useDispatch()
  const accessToken = localStorage.getItem('access_token')
  const userInfo = localStorage.getItem('user_info')
  dispatch(getToken(JSON.parse(accessToken)))
  dispatch(getUserInfo(JSON.parse(userInfo)))

  return (
    <Router>
      <div className="application">
        <ScrollToTop/>
        <Header/>
        <Main>
          <Switch>
            <Route exact path="/" component={MainPage}/>
            <PublicRoute restricted={true} component={SignInPage} path="/sign-in"/>
            <PublicRoute restricted={true} component={SignUpPage} path="/sign-up"/>
            <PublicRoute restricted={true} component={ResetPasswordPage} path="/password-reset"/>
            <PrivateRoute component={AccountPage} path="/account" />
            <PrivateRoute component={OrdersPage} path="/orders" />
            <PrivateRoute component={OrderPage} path="/order/:id" />
            <ProtectedRoute component={CreateOrderPage} path="/create-order"/>
            <Route
              path="*"
              component={NotFound}/>
          </Switch>
        </Main>
        <Footer/>
      </div>
    </Router>
  );
}

export default Application;