export function handleClientChange(client) {
  console.log('handleClientChange', client);
  return {
    type: "SET_INPUT_CLIENT",
    payload: client,
  };
}

export function handleProductChange(externalId) {
  return {
    type: "SET_INPUT_PRODUCT",
    payload: externalId,
  };
}
