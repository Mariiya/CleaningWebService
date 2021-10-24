//generals
import React from 'react';
import {Route, Switch, BrowserRouter as Router,} from "react-router-dom";
//components
import Header from '../Header/Header';
import Main from '../Main/Main';
import Footer from "../Footer/Footer";
//pages
import MainPage from '../../pages/MainPage/MainPage';
import SignInPage from "../../pages/SignInPage/SignInPage";
import SignUpPage from "../../pages/SignUpPage/SignUpPage";
import ResetPasswordPage from "../../pages/ResetPasswordPage/ResetPasswordPage"
//styles
import './Application.scss';

function Application() {
  return(
    <Router>
      <div className="application">
        <Header/>
        <Main>
          <Switch>
            <Route exact path="/" component={MainPage}/>
            <Route path="/sign-in" component={SignInPage}/>
            <Route path="/sign-up" component={SignUpPage}/>
            <Route path="/password-reset" component={ResetPasswordPage}/>
          </Switch>
        </Main>
        <Footer/>
      </div>
    </Router>
  );
}

export default Application;