import React from "react/lib/React";

import Footer from "./Footer";
import Header from "./Header";
import SearchCriteriaForm from "./SearchCriteriaForm";
import SearchResultsForm from "./SearchResultsForm";

export default class Layout extends React.Component {
  constructor() {
    super();
    this.state = {
      client: "Default client!",
      product: "Default product!"
    };
  }

  handleResult(client, product) {
    this.setState({client, product});
  }

  render() {
    return (
      <div class="container">
        <Header />
        <SearchCriteriaForm onUpdate={this.handleResult.bind(this)} />
        <SearchResultsForm client={this.state.client} product={this.state.product} />
        {/*<Footer />*/}
      </div>
    );
  }
}
