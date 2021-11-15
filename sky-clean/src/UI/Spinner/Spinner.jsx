//general
import React from "react";
//styles
import "./Spinner.scss";

function Spinner({
 size = 40,
 color = "#01A0DA",
 strokeWidth = 6,
}) {
  
  const classes = {
    spinner: {
      width: size,
      height: size,
      border: `${strokeWidth}px solid #bbbbbb77`,
      borderTop: `${strokeWidth}px solid ${color}`,
    },
  };
  
  return (
    <div className="spinner__wrapper">
      <div style={classes.spinner} className="spinner__circle" />
    </div>
  )
}

export default Spinner
