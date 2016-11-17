import React from "react/lib/React";

import Header from "./Header";
import SearchCriteriaForm from "./SearchCriteriaForm";

export default class Layout extends React.Component {
  render() {
    return (
      <div>
        <Header />
        <SearchCriteriaForm />
      </div>
    );
  }
}
