<template>
  <div class="signUpModal">
    <form v-on:submit.prevent="signUpUser" style="border:1px solid #ccc">
      <div class="container">
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <p class="errorText">{{errorMsg}}</p>

        <label><b>Nickname</b></label>
        <input type="text" placeholder="enter nickname" v-model="signUpDetails.nickName" required>

        <label><b>Email</b></label>
        <input type="email" placeholder="enter email" v-model="signUpDetails.email" required>

        <label><b>FirstName</b></label>
        <input type="text" placeholder="enter FirstName" v-model="signUpDetails.firstName" required>

        <label><b>LastName</b></label>
        <input type="text" placeholder="enter LastName" v-model="signUpDetails.lastName" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="enter password" name="psw-repeat" v-model="signUpDetails.password" required>

        <label><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat password" name="psw-repeat" v-model="signUpDetails.repeatPassword" required>

        <div class="clearfix">
          <button type="submit" class="signUpBtn">Sign Up</button>
        </div>
        </div>
    </form>
    <div>
      <p>Already have account?</p>
      <button type="button" class="redirectToLogin" v-on:click="redirectToLogin()">Login</button>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import {APIENDPOINT} from "../../app.config";

  export default {
    name: "signUp",
    data() {
      return {
        signUpDetails : {
          nickName : '',
          email: '',
          firstName: '',
          lastName: '',
          password : '',
          repeatPassword: ''
        },
        errorMsg: ''
      }
    },
    methods: {
      signUpUser:function() {
        if(!this.validatePasswords())
          return;

        const authUser = {};
        var app = this;
        const newUser = this.makeRequestBody();

        axios.post(APIENDPOINT + '/signup', newUser)
          .then(response=>{
            authUser.nickName = app.signUpDetails.nickName;
            authUser.token = response.data;
            window.localStorage.setItem('lbUser',JSON.stringify(authUser));
            app.$router.push('/profile');
          })
          .catch(err=>{
            if(err.response) {
              if(err.response.status == 422)
                 app.errorMsg = "Email or nickname already taken";
            }
           });
      },
      signUpAuth:function () {
        var app = this;
        const alreadySignedUp =  app.$store.state.isLoggedIn;
        if(!alreadySignedUp) {
          app.$router.push('/signup');
        }
        else {
          app.$router.push('/profile');
        }
      },
      validatePasswords: function () {
          if(this.signUpDetails.password != this.signUpDetails.repeatPassword){
            this.errorMsg = "Passwords do not match!";
            this.signUpDetails.password = '';
            this.signUpDetails.repeatPassword = '';
            return false;
          }
          return true;
      },
      makeRequestBody: function()
      {
        return {
          nickName: this.signUpDetails.nickName,
          email: this.signUpDetails.email,
          firstName: this.signUpDetails.firstName,
          lastName: this.signUpDetails.lastName,
          password: this.signUpDetails.password
        }
      },
      redirectToLogin: function () {
        this.$router.push('/login');
      }
    },
    created:function() {
      this.signUpAuth();
    }
  }
</script>

<style>

  .errorText{
    color: red;
  }

  .signUpModal {
    padding: 0rem 15rem;
  }

  /* Full-width input fields */
  input[type=text], input[type=password], input[type=email] {
    width: 90%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
  }

  input[type=text]:focus, input[type=password]:focus, input[type=email]:focus{
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
  .cancelbtn, .signUpBtn {
    float: left;
    width: 50%;
  }

  .redirectToLogin{
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
    .cancelbtn, .signUpBtn {
      width: 100%;
    }
  }

  @media only screen and (max-width: 920px) {
    .signUpModal {
      padding: 0;
    }
  }

</style>
