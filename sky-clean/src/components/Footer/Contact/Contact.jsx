//general
import React from 'react';
//styles
import './Contact.scss';

function Contact({contact}) {
  return (
    <div className="contact">
      <div className="contact__header">
        <img
          className="contact__img"
          src={contact.src}
          alt={contact.type}
        />
        <h3 className="contact__title">
          {contact.title}
        </h3>
      </div>

      <ul className="contact__list">
        {contact.list.map((item) => <li key={item} className="contact__item">{item}</li>)}
      </ul>
    </div>
  );
};

export default Contact;