<template>
<div class="loginModal">
  <form v-on:submit.prevent="loginUser()" style="border:1px solid #ccc">
    <div class="container">
      <h1>Login</h1>
      <p>Welcome back bro!</p>
      <p>Bro we jsut need your fu**ing nick and pass</p>
      <p>You know what to do right?</p>
      <hr>

      <label><b>Nickname</b></label>
      <input type="text" placeholder="enter username" v-model="loginDetails.nickName" required>

      <label><b>Password</b></label>
      <input type="password" placeholder="enter password" v-model="loginDetails.password" required>

      <div class="clearfix">
        <button type="button" class="cancelbtn" v-on:click="cancelLogin()">Sorry ma</button>
        <button type="submit" class="loginBtn">Login</button>
      </div>
      <button type="button" class="redirectToSignUp" v-on:click="redirectToSignUp()">Sign Up</button>
    </div>
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
          nickName : '',
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
              console.log("res.data " + res.data.nickName);
              authUser.data = res.data;
            //  authUser.token = res.token;
              app.$store.state.isLoggedIn = true;
              window.localStorage.setItem('lbUser',JSON.stringify(authUser));
              app.$router.push('/profile');
          })
          .catch(function (err){
            console.log(err);
          })
      },
      loginAuth:function () {
        var app = this;
        const status =  JSON.parse(window.localStorage.getItem('lbUser'));
        if(status === null || status === undefined) {
          app.$router.push('/login');
        }else {
          app.$router.push('/profile');
        }
      },
      cancelLogin: function () {
        this.$router.push('/');
      },
      redirectToSignUp: function () {
        this.$router.push('/signUp');
      }
    },
    created:function() {
      this.loginAuth();
    }
  }
</script>

<style >

  /* Full-width input fields */
  input[type=text], input[type=password] {
    width: 90%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
  }

  input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
  }

  hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
  }

  /* Set a style for all buttons */
  button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
  }

  button:hover {
    opacity:1;
  }

  /* Extra styles for the cancel button */
  .cancelbtn {
    padding: 14px 20px;
    background-color: #f44336;
  }

  /* Float cancel and signup buttons and add an equal width */
  .cancelbtn, .loginBtn {
    float: left;
    width: 50%;
  }

  .redirectToSignUp{
    float: left;
    width: 30%;
    background-color: #57e7f4;
  }

  /* Add padding to container elements */
  .container {
    padding: 16px;
  }

  /* Clear floats */
  .clearfix::after {
    content: "";
    clear: both;
    display: table;
  }

  /* Change styles for cancel button and signup button on extra small screens */
  @media screen and (max-width: 300px) {
    .cancelbtn, .loginBtn, .redirectToSignUp {
      width: 70%;
    }
  }


  .loginModal {
    padding: 0rem 15rem;
  }


  @media only screen and (max-width: 850px) {
    .loginModal {
      padding: 0;
    }
  }


</style>
