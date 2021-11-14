//generals
import React from 'react';
import {Route, Switch, BrowserRouter as Router} from "react-router-dom";
//redux
import {useDispatch, useSelector} from "react-redux";
import {getToken, getUserInfo} from "../../store/user/actions";
//custom hooks
import ScrollToTop from "../../hooks/ScrollToTop";
import PrivateRoute from "../../hooks/PrivateRoute";
import ProtectedRoute from "../../hooks/ProtectedRoute";
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
import CreateOrderPage from "../../pages/CreateOrderPage/CreateOrderPage";

function Application() {
  const dispatch = useDispatch()
  const isAuth = useSelector((state) => state.user.accessToken)
  
  React.useEffect(() => {
    const accessToken = localStorage.getItem('access_token')
    const userInfo = localStorage.getItem('user_info')
    dispatch(getToken(JSON.parse(accessToken)))
    dispatch(getUserInfo(JSON.parse(userInfo)))
  }, [dispatch])
  
  return (
    <Router>
      <div className="application">
        <ScrollToTop/>
        <Header/>
        <Main>
          <Switch>
            <Route exact path="/" component={MainPage}/>
            <ProtectedRoute path="/sign-in" children={<SignInPage/>} isAuth={isAuth}/>
            <ProtectedRoute path="/sign-up" children={<SignUpPage/>} isAuth={isAuth}/>
            <ProtectedRoute path="/password-reset" children={<ResetPasswordPage/>} isAuth={isAuth}/>
            <PrivateRoute path="/orders" children={<OrdersPage/>} isAuth={isAuth}/>
            <PrivateRoute path="/create-order" children={<CreateOrderPage/>} isAuth={isAuth}/>
            <Route path="*" component={NotFound}/>
          </Switch>
        </Main>
        <Footer/>
      </div>
    </Router>
  );
}

export default Application;