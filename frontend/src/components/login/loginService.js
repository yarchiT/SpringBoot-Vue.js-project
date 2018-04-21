import Vue from 'vue';
import { APIENDPOINT , getHeader } from '../../app.config';
import axios from 'axios';
export default {
  login(value, cb) {
    return new Promise(function (resolve, reject) {
      var user={};
      user.data = {
        role_id: "user",
        name: "yara",
        nickname: "yarik"
      };
      user.status = "success";
   user.token = {};
   resolve(user);
     /* axios.post(APIENDPOINT + '/auth/login', value)
        .then(function (res) {
          resolve(res.data);
        })
        .catch(function (err) {

          reject("error")
        })*/
    });

  }
}
