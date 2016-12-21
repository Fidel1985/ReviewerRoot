import { combineReducers } from "redux"

import input from "./inputReducer"
import result from "./resultReducer"

export default combineReducers({
  input,
  result,
})