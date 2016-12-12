import React from "react/lib/React";

import FormGroup from "react-bootstrap/lib/FormGroup";
import InputGroup from "react-bootstrap/lib/InputGroup";
import FormControl from "react-bootstrap/lib/FormControl";
import Button from "react-bootstrap/lib/Button";

import * as Actions from "../actions/Actions";
import DataStore from "../stores/DataStore";

import Result from "./Result";

export default class SearchCriteriaForm extends React.Component {
  constructor() {
    super();
    this.getData = this.getData.bind(this);
    this.state = {
      data: DataStore.getAll()
    };
  }

  componentWillMount() {
    DataStore.on("change", this.getData);
  }

  componentWillUnmount() {
    DataStore.removeListener("change", this.getData);
  }

  getData() {
    this.setState({
      data: DataStore.getAll(),
    });
  }

  handleClientChange(event) {
    if (event.target) {
      Actions.handleClientChange(event.target.value);
    }
  }

  handleProductChange(event) {
    if (event.target) {
      Actions.handleProductChange(event.target.value);
    }
  }

  handleSubmit() {
    var theClientVal = this.refs.clientInput.props.value;
    var theProductVal = this.refs.productInput.props.value;
    Actions.loadData(theClientVal, theProductVal);
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
                  <FormControl type="text" ref="clientInput" value={this.state.data.client}
                               onChange={this.handleClientChange.bind(this)}/>
                  <InputGroup.Addon>product</InputGroup.Addon>
                  <FormControl type="text" ref="productInput" value={this.state.data.externalId}
                               onChange={this.handleProductChange.bind(this)}/>
                  <InputGroup.Button>
                    <Button onClick={this.handleSubmit.bind(this)}>Search</Button>
                  </InputGroup.Button>
                </InputGroup>
              </div>
            </FormGroup>
          </form>
        </div>
        {this.state.data.show == true &&
        <Result client={this.state.data.client} externalId={this.state.data.externalId}
                native={this.state.data.native} syndicated={this.state.data.syndicated}
        />
        }
      </div>
    );
  }
}