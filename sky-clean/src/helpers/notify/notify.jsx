//general
import React from "react";
import { toast } from "react-toastify";
//styles
import './notify.scss'

const defaultOptions = {
  position: "bottom-right",
  autoClose: 5000,
  hideProgressBar: true,
  closeOnClick: true,
  pauseOnHover: false,
  draggable: true,
  progress: undefined,
  icon: false
}

function createContent(title, message) {
  return (
    <>
      <figure className="toast-circle-figure" />
      <div className="toast-body-content">
        <span className="toast-body-title">{title}</span>
        <span className="toast-body-text">{message}</span>
      </div>
    </>
  );
}

function createNotifyByType(type) {
  return (title, message, options = defaultOptions) => {
    toast[type](createContent(title, message), options);
  };
}

function createErrorsList() {
  return (errors, options = defaultOptions) => {
    if (Array.isArray(errors) && errors.length) {
      errors.forEach(({ message }) =>
        toast.error(createContent("Error", message), options)
      );
    }
  };
}

const notify = (title, message, options = defaultOptions) => {
  toast.success(createContent(title, message), options);
};

notify.error = createNotifyByType("error");
notify.info = createNotifyByType("info");
notify.errorsList = createErrorsList();

export { notify };
