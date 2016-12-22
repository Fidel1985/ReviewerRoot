import axios from "axios";

export function fetchResult(theClientVal, theProductVal) {
  const URL = "http://localhost:8082/product/" + theClientVal + "/" + theProductVal;
  return function (dispatch) {
    axios.get(URL)
      .then((response) => {
        dispatch({type: "FETCH_RESULT_FULFILLED", payload: response.data});
      })
      .catch((error) => {
        dispatch({type: "FETCH_RESULT_REJECTED", payload: error})
      })
  }
}