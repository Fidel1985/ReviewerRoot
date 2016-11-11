import React from "react/lib/React";
import Label from "react-bootstrap/lib/Label";

export default class Header extends React.Component {

  render() {
    const containerStyle = {
      textAlign: "center",
      marginTop: "60px",
      background: "#4682B4"
    };

    return (
      <div class="container" style={containerStyle}>
        {/*<h1><Label>Board Of Syndication Statistics</Label></h1>*/}
        <h1>Board Of Syndication Statistics</h1>
      </div>
    );
  }
}
