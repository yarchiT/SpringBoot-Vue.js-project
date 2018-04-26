import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    isLoggedIn: localStorage.getItem('lbUser') != null,
    getToken: function() {
      let user =  JSON.parse(localStorage.getItem('lbUser'));
      let token = user == null ? '' : user.token;
      return token;
    },
    getNickName: function () {
      let user =  JSON.parse(localStorage.getItem('lbUser'));
      let nickName = user == null ? '' : user.nickName;
      return nickName;
    }
    }
});
