//generals
import React from 'react';
import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
//custom hooks
import ScrollToTop from "../../hooks/ScrollToTop";
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
  return(
    <Router>
      <div className="application">
        <ScrollToTop/>
        <Header/>
        <Main>
          <Routes>
            <Route exact path="/" element={<MainPage/>}/>
            <Route path="/sign-in" element={<SignInPage/>}/>
            <Route path="/sign-up" element={<SignUpPage/>}/>
            <Route path="/password-reset" element={<ResetPasswordPage/>}/>
            <Route path="/orders" element={<OrdersPage/>}/>
            <Route path="*" element={<NotFound/>} />
          </Routes>
        </Main>
        <Footer/>
      </div>
    </Router>
  );
}

export default Application;