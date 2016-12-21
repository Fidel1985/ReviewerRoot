export default function reducer(state={
    input: {
      client: null,
      product: null,
    },
    fetching: false,
    fetched: false,
    error: null,
  }, action) {

    switch (action.type) {
      case "FETCH_INPUT": {
        return {...state, fetching: true}
      }
      case "FETCH_INPUT_REJECTED": {
        return {...state, fetching: false, error: action.payload}
      }
      case "FETCH_INPUT_FULFILLED": {
        return {
          ...state,
          fetching: false,
          fetched: true,
          input: action.payload,
        }
      }
      case "SET_INPUT_CLIENT": {
        return {
          ...state,
          input: {...state.input, client: action.payload},
        }
      }
      case "SET_INPUT_PRODUCT": {
        return {
          ...state,
          input: {...state.input, product: action.payload},
        }
      }
    }

    return state;
}
