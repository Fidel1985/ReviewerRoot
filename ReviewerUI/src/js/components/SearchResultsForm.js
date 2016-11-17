import React from "react/lib/React";

export default class SearchResultsForm extends React.Component {
  render() {
    return (
      <div>
        <div className="container" id="outer-block">
          <h4>Results</h4>
          <div id="inner-block">
            <div><h4>client: {this.props.client}</h4></div>
            <div><h4>product: {this.props.product}</h4></div>
          </div>
        </div>
      </div>
    )
  }
}
