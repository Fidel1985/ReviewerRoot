import React from "react/lib/React";
import FormGroup from "react-bootstrap/lib/FormGroup";
import InputGroup from "react-bootstrap/lib/InputGroup";
import FormControl from "react-bootstrap/lib/FormControl";
import Button from "react-bootstrap/lib/Button";

function Result(props) {
    return (
        <div>
          <div className="container" id="outer-block">
            <h4>Results</h4>
            <div id="inner-block">
              <div><h4>client: {props.client}</h4></div>
              <div><h4>product: {props.product}</h4></div>
            </div>
          </div>
        </div>
    )
}

export default class SearchCriteriaForm extends React.Component {
  constructor() {
    super();
    this.state = {
      client: "",
      product: "",
      childClient: "",
      childProduct: ""
    };
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
    this.setState({childClient: theClientVal});
    this.setState({childProduct: theProductVal});
    event.preventDefault();
  }

  render() {
    return (
      <div>
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
                               onChange={this.handleClientChange.bind(this)}/>
                  <InputGroup.Addon>product</InputGroup.Addon>
                  <FormControl type="text" ref="productInput" value={this.state.product}
                               onChange={this.handleProductChange.bind(this)}/>
                  <InputGroup.Button>
                    <Button onClick={this.handleSubmit.bind(this)}>Search</Button>
                  </InputGroup.Button>
                </InputGroup>
              </div>
            </FormGroup>
          </form>
        </div>
          { this.state.childClient !="" && this.state.childProduct !="" &&
            <Result client={this.state.childClient} product={this.state.childProduct} />
          }
      </div>
    );
  }
}