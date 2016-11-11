import React from "react";

export default class SearchCriteriaForm extends React.Component {
  render() {
    const containerStyle = {
      marginTop: "20px",
      height: "500px",
      background: "#87CEEB"
    };
    return (
      <div class="container" style={containerStyle}>
          <div>client {this.props.client}</div>
          <div>product {this.props.product}</div>
      </div>
    )
  }
}
