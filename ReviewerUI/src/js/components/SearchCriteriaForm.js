import React from "react/lib/React";
import FormGroup from "react-bootstrap/lib/FormGroup";
import InputGroup from "react-bootstrap/lib/InputGroup";
import FormControl from "react-bootstrap/lib/FormControl";
import Button from "react-bootstrap/lib/Button";

export default class SearchCriteriaForm extends React.Component {
  constructor() {
    super();
    this.state = {
      client: "",
      product: "",
    };

    this.handleClientChange = this.handleClientChange.bind(this);
    this.handleProductChange = this.handleProductChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleClientChange(event) {
    if (event.target) {
      this.setState({client: event.target.value,});
    }
  }

  handleProductChange(event) {
    if (event.target) {
      this.setState({product: event.target.value});
    }
  }

  handleSubmit(event) {
    var theClientVal = this.refs.clientInput.props.value;
    var theProductVal = this.refs.productInput.props.value;
    this.props.onUpdate(theClientVal, theProductVal);
    event.preventDefault();
  }

  render() {
    return (
      <div className="container">
        <form>
          <FormGroup>
            <div className="col-md-3">
              <h4>Select search criteria:</h4>
            </div>
            <div className="col-md-8">
              <InputGroup>
                <InputGroup.Addon>client</InputGroup.Addon>
                <FormControl type="text" ref="clientInput" value={this.state.client}
                             onChange={this.handleClientChange}/>
                <InputGroup.Addon>product</InputGroup.Addon>
                <FormControl type="text" ref="productInput" value={this.state.product}
                             onChange={this.handleProductChange}/>
                <InputGroup.Button>
                  <Button onClick={this.handleSubmit}>Search</Button>
                </InputGroup.Button>
              </InputGroup>
            </div>
          </FormGroup>
        </form>
      </div>
    );
  }
}