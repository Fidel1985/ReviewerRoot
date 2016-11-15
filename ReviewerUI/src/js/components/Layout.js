import React from "react/lib/React";

import Header from "./Header";
import SearchResultsForm from "./SearchResultsForm";

export default class Layout extends React.Component {
  render() {
    return (
      <div>
        <Header />
        <SearchResultsForm />
      </div>
    );
  }
}
