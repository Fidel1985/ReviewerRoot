import React from "react/lib/React";

export default class Header extends React.Component {

  render() {
    const containerStyle = {
      textAlign: "center",
      marginTop: "60px",
      background: "#729FCF"
    };

    return (
      <div class="container" style={containerStyle}>
        <h1>Board Of Syndication Statistics</h1>
      </div>
    );
  }
}
