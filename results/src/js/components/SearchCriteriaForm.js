import React from "react";

export default class SearchCriteriaForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      client: "",
      product: "",
    };

    this.handleClientChange = this.handleClientChange.bind(this);
    this.handleProductChange = this.handleProductChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleClientChange(event) {
    this.setState({client: event.target.value});
  }

  handleProductChange(event) {
    this.setState({product: event.target.value});
  }

  handleSubmit(event) {
    alert('search criteria was submitted: ' + 'client ' + this.state.client + ' product ' + this.state.client);
    event.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        Select search criteria:
        <input type="text" value={this.state.client} onChange={this.handleClientChange} />
        <input type="text" value={this.state.product} onChange={this.handleProductChange} />
        <input type="submit" value="Submit" />
      </form>
    );
  }
}