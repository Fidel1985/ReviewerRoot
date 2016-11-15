import React from "react/lib/React";

import SearchCriteriaForm from "./SearchCriteriaForm";

export default class SearchResultsForm extends React.Component {
  constructor() {
    super();
    this.state = {
      client: "",
      product: ""
    };
  }

  handleResult(client, product) {
    this.setState({client : client, product : product});
  }

  render() {
    return (
      <div>
        <SearchCriteriaForm onUpdate={this.handleResult.bind(this)}/>
        <div className="container" id="outer-block">
          <h4>Results</h4>
          <div id="inner-block">
            <div><h4>client: {this.state.client}</h4></div>
            <div><h4>product: {this.state.product}</h4></div>
          </div>
        </div>
      </div>
    )
  }
}
