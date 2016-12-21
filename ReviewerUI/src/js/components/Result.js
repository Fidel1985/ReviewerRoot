import React from "react";

export default function Result(props) {
  return (
    <div>
      <div className="container" id="outer-block">
        <h4>Results</h4>
        <div id="inner-block">
          <div><h4>client: {props.client}</h4></div>
          <div><h4>product: {props.externalId}</h4></div>
          <div><h4>native: {props.native}</h4></div>
          <div><h4>syndicated: {props.syndicated}</h4></div>
        </div>
      </div>
    </div>
  )
}