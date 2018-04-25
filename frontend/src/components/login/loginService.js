import Vue from 'vue';
import { APIENDPOINT , getHeader } from '../../app.config';
import axios from 'axios';
export default {
  login(loginDetails) {
    return new Promise(function (resolve, reject) {
      var bodyFormData = new FormData();
      bodyFormData.set('nickName', loginDetails.nickName);
      bodyFormData.set('password', loginDetails.password);
      console.log("loginDetails.password " +loginDetails.password);

      axios.post(APIENDPOINT + '/users/login', bodyFormData)
        .then(function (res) {
          resolve(res);
        })
        .catch(function (err) {
          reject("login error")
        })
    });

  }
}
