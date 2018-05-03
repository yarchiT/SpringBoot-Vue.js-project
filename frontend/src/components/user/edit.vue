<template>
  <div class="container">
    <form v-on:submit.prevent="submit">
      <div class="row">

        <div class="col-25">
          <label for="fnickname">Nickname</label>
        </div>
        <div class="col-75">
          <input type="text" id="fnickname" name="nickname" placeholder="Your nickname.." v-model="user.nickName" readonly>
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="firstname">First Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="firstname" name="firstname" placeholder="Your first name name.."  v-model="user.firstname">
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="lastname">Last Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="lastname" name="lastname" placeholder="Your last name name.."  v-model="user.lastname">
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="lemail">Email</label>
        </div>
        <div class="col-75">
          <input type="text" id="lemail" name="email" placeholder="Your email.." v-model="user.email" readonly>
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="country">Gender</label>
        </div>
        <div class="col-75">
          <select id="country" v-model="user.gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="about">About</label>
        </div>
        <div class="col-75">
          <textarea id="about" placeholder="Write something about yourself.." style="height:200px" v-model="user.about"></textarea>
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Edit">
      </div>
    </form>
  </div>
</template>

<script>
  import axios from 'axios';
  import {APIENDPOINT} from '../../app.config';


  export default {
    name: 'edit' ,// this is the name of the component
    data(){
      return {
        showPreview: false,
        user:{
          nickName:"",
          firstname:"",
          lastname:"",
          email:"",
          gender:"",
          about:""
        }
      }

    },
    methods: {
      submit(){

        let formData = new FormData();
        formData.append('nickName',this.user.nickName);
        formData.append('fullName', this.user.fullName);
        formData.append('email',this.user.email);
        formData.append('gender',this.user.gender);
        formData.append('about',this.user.about);

        var editUser = {
          lastName: this.user.lastname,
          firstName: this.user.firstname,
          gender: this.user.gender,
          bio: this.user.about
        };

        axios.put(APIENDPOINT + '/users/' + this.user.nickName, editUser)
          .then(resp=>{
            console.log('updated!')
            this.$swal('', 'Succssefully edited!', 'OK');
          }).catch(ex => {
          console.log('exception : ' + ex)
          this.$swal('', 'Failure in editing', 'OK');
        });
      }
    },
    created() {
      var app = this;
      axios.get(APIENDPOINT + '/users/' + this.$store.state.getNickName())
        .then(response=>{
        app.user.nickName = response.data.nickName;
        app.user.lastname = response.data.lastName;
        app.user.firstname = response.data.firstName;
        app.user.email = response.data.email;
        app.user.gender = response.data.gender;
        app.user.about = response.data.bio;
      }).catch(err=>{

      })
    }
  }


</script>
<style>
  * {
    box-sizing: border-box;
  }

  input[type=text], select, textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
  }

  label {
    padding: 12px 12px 12px 0;
    display: inline-block;
  }

  input[type=submit] {
    margin-top: 2%;
    background-color: dodgerblue;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
  }

  input[type=submit]:hover {
    background-color: dodgerblue;
  }

  .container {
    margin-top: 3%;
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
  }

  .col-25 {
    float: left;
    width: 25%;
    margin-top: 6px;
  }

  .col-75 {
    float: left;
    width: 75%;
    margin-top: 6px;
  }

  /* Clear floats after the columns */
  .row:after {
    content: "";
    display: table;
    clear: both;
  }

  img {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
    width: 150px;
  }

  img:hover {
    box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
  }

  /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
  @media screen and (max-width: 600px) {
    .col-25, .col-75, input[type=submit] {
      width: 100%;
      margin-top: 0;
    }
  }

</style>
