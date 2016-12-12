import dispatcher from "../dispatcher";

export function handleClientChange(client) {
  dispatcher.dispatch({type: "HANDLE_CLIENT_DATA", client: client});
}

export function handleProductChange(externalId) {
  dispatcher.dispatch({type: "HANDLE_PRODUCT_DATA", externalId: externalId});
}

export function loadData(theClientVal, theProductVal) {
  const URL = "http://localhost:8082/product/" + theClientVal + "/" + theProductVal;
  fetch(URL).then(function (response) {
    return response.json();
  }).then(function (data) {
    console.log('request succeeded with JSON response', data);
    data.show = true;
    dispatcher.dispatch({type: "RECEIVE_DATA", data: data});
  }).catch(function (error) {
    alert('request failed' + error)
  })

}
