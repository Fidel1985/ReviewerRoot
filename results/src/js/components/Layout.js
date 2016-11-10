import React from "react";

import Footer from "./Footer";
import Header from "./Header";
import SearchCriteriaForm from "./SearchCriteriaForm";
import SearchResultsForm from "./SearchResultsForm";

export default class Layout extends React.Component {
  constructor() {
    super();
    this.state = {
      title: "Welcome",
    };
  }

  changeTitle(title) {
    this.setState({title});
  }

  render() {
    return (
      <div>
        <Header />
        <SearchCriteriaForm />
        <SearchResultsForm />
        {/*<Footer />*/}
      </div>
    );
  }
}
