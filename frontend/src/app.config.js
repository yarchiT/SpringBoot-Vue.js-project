import axios from 'axios';
import { store }from '../src/components/store';
export const APIENDPOINT = "http://localhost:8181/api";

axios.defaults.headers.common = {
  "Accept": "application/json",
  "Authorization": ("Bearer " + store.state.getToken()).replace(/['"]+/g, ''),
  "Access-Control-Allow-Credentials": true
};


