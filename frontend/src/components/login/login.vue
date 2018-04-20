<template>
  <div class="login">
    <h1>Login Page</h1>
    <p v-model="errorMsg"></p>
    <form v-on:submit.prevent="loginUser()">
      <input type="text" placeholder="enter username" v-model="loginDetails.residentID">
      <input type="password" placeholder="enter password" v-model="loginDetails.password">
      <button type="submit" value="login" >LOGIN</button>
    </form>
  </div>

</template>

<script>
  import loginService from './loginService.js';

  export default {
  name: "login",
    data() {
      return {
        loginDetails : {
          residentID : '',
          password : ''
        },
        errorMsg: ''
      }
    },
    methods: {
      loginUser:function() {
        const authUser = {};
        var app = this;
        loginService.login(this.loginDetails)
          .then(function(res) {
            if(res.status === "success") {
              console.log("logged in");
              authUser.data = res.data;
              authUser.token = res.token;
              app.$store.state.isLoggedIn = true;
              window.localStorage.setItem('lbUser',JSON.stringify(authUser));
              if(authUser.data.role_id === 'ADMIN') {
                app.$router.push('/admin');
              }else {
                app.$router.push('/user');
              }
            }else {
              app.$store.state.isLoggedIn = false;
            }
          })
          .catch(function (err){
            this.errorMsg = "sdf";
            console.log(err + "pisun");
          })
      },
      loginAuth:function () {
        var app = this;
        const status =  JSON.parse(window.localStorage.getItem('lbUser'));
        if(status === null || status === undefined) {
          app.$router.push('/login');
        }else if (status.data.role_id === 'ADMIN') {
          app.$router.push('/admin');
        }else {
          app.$router.push('/resident');
        }
      }
    },
    created:function() {
      this.loginAuth();
    }
  }
</script>

<style >
  .login {
    padding: 2rem 2rem;
  }
  h1 {
    color: purple;
    padding: 1rem 0rem;
  }
  form input {
    height: 40px;
    display: block;
    margin :10px 0;
    width: 300px;
  }
  form button {
    height: 30px;
    width: 120px;
  }

</style>
