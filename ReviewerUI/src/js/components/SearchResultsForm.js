import React from "react/lib/React";

export default class SearchCriteriaForm extends React.Component {
  render() {
    const outer = {
      marginTop: "20px",
      height: "500px",
      background: "#CFE7F5"
    };
    const inner = {
      marginTop: "20px",
      background: "#729FCF"
    };
    return (
      <div class="container" style={outer}>
          <h4>Results</h4>
          <div style={inner}>
            <div><h4>client: {this.props.client}</h4></div>
            <div><h4>product: {this.props.product}</h4></div>
        </div>
      </div>
    )
  }
}
