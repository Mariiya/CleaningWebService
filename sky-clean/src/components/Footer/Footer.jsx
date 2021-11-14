//general
import React from 'react';
//components
import Contact from "./Contact/Contact";
//styles
import './Footer.scss';
//assets
import logo from '../../assets/icons/logo.svg';
import phone from '../../assets/icons/footer-phone.svg';
import mail from '../../assets/icons/footer-mail.svg'
import {NavLink} from "react-router-dom";

function Footer() {
  const contacts = [
    {src: phone, type: 'phone', title: 'Our contacts', list: ['+380 099 34 75', '+380 340 11 60']},
    {src: mail, type: 'mail', title: 'Our mails', list: ['skyclean@gmail.com', 'skyclean.support@gmail.com']},
  ];

  return (
    <footer className="footer">
      <div className="footer__wrapper wrapper">
        <div className="footer__head">
          <div className="footer__main">
            <NavLink to="/" className="footer__logo">
              <img
                className="footer__logo-img"
                src={logo}
                alt="logo"
              />
              <h2 className="footer__logo-title">
                SkyClean
              </h2>
            </NavLink>

            <nav className="footer__navigation">
              <NavLink to="/" className="footer__link">
                Main
              </NavLink>

              <NavLink to="/orders" className="footer__link">
                Orders
              </NavLink>
            </nav>
          </div>
          <div className="footer__contacts">
            {contacts.map((contact) => <Contact key={contact.type} contact={contact}/>)}
          </div>
        </div>
        <div className="footer__copyright">
          2021 © Copyright all rights saves
        </div>
      </div>
    </footer>
  );
};

export default Footer;