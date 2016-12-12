import { EventEmitter } from "events";

import dispatcher from "../dispatcher";

class DataStore extends EventEmitter {
  constructor() {
    super();
    this.data = {
      client: '',
      externalId: '',
      native: -1,
      syndicated: -1,
      show: false
    };
  }

  getAll() {
    return this.data;
  }

  handleActions(action) {
    switch(action.type) {
      case "RECEIVE_DATA": {
        this.data = action.data;
        this.emit("change");
        break;
      }
      case "HANDLE_CLIENT_DATA": {
        this.data.client = action.client;
        this.data.show = false;
        this.emit("change");
        break;
      }
      case "HANDLE_PRODUCT_DATA": {
        this.data.externalId = action.externalId;
        this.data.show = false;
        this.emit("change");
        break;
      }
    }
  }

}

const dataStore = new DataStore;
dispatcher.register(dataStore.handleActions.bind(dataStore));

export default dataStore;
