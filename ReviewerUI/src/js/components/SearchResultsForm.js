import React from "react";

export default class SearchCriteriaForm extends React.Component {
  render() {
    return (
      <div>
        <div>client {this.props.client}</div>
        <div>product {this.props.product}</div>
      </div>
    )
  }
}
