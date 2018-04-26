import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    isLoggedIn: localStorage.getItem('token') !== '',
    getToken: function() {
      let ret =  localStorage.getItem('token') || '';
      console.log('return : ' + ret)
      return ret.toString();
    }
    }
});
