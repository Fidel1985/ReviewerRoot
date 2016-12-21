export default function reducer(state={
    result: {
      client: null,
      externalId: null,
      native: null,
      syndicated: null
    },
    fetching: false,
    fetched: false,
    error: null,
  }, action) {

    switch (action.type) {
      case "FETCH_RESULT": {
        return {...state, fetching: true}
      }
      case "FETCH_RESULT_REJECTED": {
        return {...state, fetching: false, error: action.payload}
      }
      case "FETCH_RESULT_FULFILLED": {
        return {
          ...state,
          fetching: false,
          fetched: true,
          result: action.payload,
        }
      }
    }

    return state
}
