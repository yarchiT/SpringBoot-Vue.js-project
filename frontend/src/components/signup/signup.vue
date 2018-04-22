<template>
  <div class="signUpModal">
    <form v-on:submit.prevent="signUpUser()" style="border:1px solid #ccc">
      <div class="container">
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label><b>Nickname</b></label>
        <input type="text" placeholder="enter nickname" v-model="signUpDetails.userName" required>

        <label><b>Email</b></label>
        <input type="email" placeholder="enter email" v-model="signUpDetails.email" required>

        <label><b>FirstName</b></label>
        <input type="text" placeholder="enter FirstName" v-model="signUpDetails.firstName" required>

        <label><b>LastName</b></label>
        <input type="text" placeholder="enter LastName" v-model="signUpDetails.lastName" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="enter password" name="psw-repeat" v-model="signUpDetails.repeatPassword" required>

        <label><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat password" name="psw-repeat" v-model="signUpDetails.repeatPassword" required>

        <div class="clearfix">
          <button type="button" class="cancelbtn" v-on:click="cancelLogin()">Sorry ma</button>
          <button type="submit" class="signUpBtn">Sign Up</button>
        </div>
        <button type="button" class="redirectToLogin" v-on:click="redirectToLogin()">Login</button>
      </div>
    </form>
  </div>
</template>

<script>
  import signUpService from './signUpService.js';

  export default {
    name: "signUp",
    data() {
      return {
        signUpDetails : {
          userName : '',
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
        const authUser = {};
        var app = this;
        signUpService.login(this.loginDetails)
          .then(function(res) {
            if(res.status === "success") {
              console.log("signed up");
              authUser.data = res.data;
              authUser.token = res.token;
              app.$store.state.isLoggedIn = true;
              window.localStorage.setItem('lbUser',JSON.stringify(authUser));
                app.$router.push('/user');
            }else {
              app.$store.state.isLoggedIn = false;
            }
          })
          .catch(function (err){
            this.errorMsg = "sdf";
            console.log(err + "blabla");
          })
      },
      signUpAuth:function () {
        var app = this;
        const status =  JSON.parse(window.localStorage.getItem('lbUser'));
        if(status === null || status === undefined) {
          app.$router.push('/signUp');
        }
        else if (status.data.role_id === 'ADMIN') {
          app.$router.push('/admin');
        }else {
          app.$router.push('/user');
        }
      },
      cancelLogin: function () {
        this.$router.push('/');
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
