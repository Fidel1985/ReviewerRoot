import React from "react";
import { connect } from "react-redux"

import FormGroup from "react-bootstrap/lib/FormGroup";
import InputGroup from "react-bootstrap/lib/InputGroup";
import FormControl from "react-bootstrap/lib/FormControl";
import Button from "react-bootstrap/lib/Button";

import Result from "./Result";

import * as Input from "../actions/inputActions"
import { fetchResult } from "../actions/resultActions"

@connect((store) => {
  return {
    input: store.input.input,
    result: store.result.result,
    resultFetched: store.result.fetched,
  };
})
export default class SearchCriteriaForm extends React.Component {
  handleClientChange(event) {
    if (event.target) {
      this.props.dispatch(Input.handleClientChange(event.target.value));
    }
  }

  handleProductChange(event) {
    if (event.target) {
      this.props.dispatch(Input.handleProductChange(event.target.value));
    }
  }

  handleSubmit() {
    var theClientVal = this.refs.clientInput.props.value;
    var theProductVal = this.refs.productInput.props.value;
    this.props.dispatch(fetchResult(theClientVal, theProductVal));
  }

  render() {
    const {input, result, resultFetched} = this.props;

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
                  <FormControl type="text" ref="clientInput" value={input.client}
                               onChange={this.handleClientChange.bind(this)}/>
                  <InputGroup.Addon>product</InputGroup.Addon>
                  <FormControl type="text" ref="productInput" value={input.product}
                               onChange={this.handleProductChange.bind(this)}/>
                  <InputGroup.Button>
                    <Button onClick={this.handleSubmit.bind(this)}>Search</Button>
                  </InputGroup.Button>
                </InputGroup>
              </div>
            </FormGroup>
          </form>
        </div>
        {resultFetched == true &&
        <Result client={result.client} externalId={result.externalId} native={result.native} syndicated={result.syndicated}
        />
        }
      </div>
    );
  }
}