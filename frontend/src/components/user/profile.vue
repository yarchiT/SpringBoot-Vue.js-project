<template>
  <div>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <div class="w3-content w3-margin-top" style="max-width:1400px;">

      <!-- The Grid -->
      <div class="w3-row-padding">

        <!-- Left Column -->
        <div class="w3-third">

          <div class="w3-white w3-text-grey w3-card-4">
            <div class="w3-display-container">
              <img :src="userDetails.avatarUrl" style="width:100%" alt="Avatar">
              <div class="w3-display-bottomleft w3-container w3-text-white" style="background-color: #278a99">
                <h2>{{userDetails.nickName}}</h2>
              </div>
            </div>
            <div class="w3-container">
              <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>{{userDetails.firstName + " " + userDetails.lastName}}</p>
              <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>{{userDetails.email}}</p>
              <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>{{userDetails.gender}}</p>
              <hr>

              <p class="w3-large"><b><i class="fa fa-asterisk fa-fw w3-margin-right w3-text-teal"></i>About</b></p>
              <p>{{userDetails.bio}}</p>
            </div>
          </div><br>

          <!-- End Left Column -->
        </div>
        <!--Right column-->
        <div class="w3-twothird">
          <AddKek></AddKek>
          <Keks v-bind:keks="userDetails.keks"></Keks>
          <!--add timeline here-->

        </div>
     </div>
  </div>


  </div>
</template>

<script>
  import Keks from './keks.vue';
  import AddKek from './addkek';

  import userService from './userService'

  export default {
    name: 'profile', //this is the name of the component
    data(){
        return{
            userDetails:{
                nickName:'',
                firstName: '',
                lastName:'',
                email:'',
                avatarUrl:'',
                gender:'',
                bio:'',
                keks:[]
            }
        }
    },
    components:{
        'Keks': Keks,
        'AddKek': AddKek
    },
    methods:{
        fillUserProfileInfo: function (userData) {
            this.userDetails.nickName = userData.nickName,
            this.userDetails.firstName = userData.firstName,
            this.userDetails.lastName = userData.lastName,
            this.userDetails.gender = userData.gender,
            this.userDetails.email = userData.email,
            this.userDetails.avatarUrl = "http://localhost:8181/static/img/user.1a5dfca.jpg",
            this.userDetails.bio = userData.bio;
        },
      getImgUrl(pet) {
        var images = require.context('../../assets/');
        return images('./' + pet )
      },

      getLoysImg(){
            return this.getImgUrl("loys.svg")
      },

      getDisLoysImg(){
        return this.getImgUrl("disloys.svg")
      },
        getUserKeks: function () {

        }
    },
    created() {
      var currentUser = JSON.parse(window.localStorage.getItem('lbUser'));
      if(currentUser == null)
        return;

      var userData = {};
      var profile = this;
      userService.getUserInfo(currentUser.nickName)
        .then(function (res) {
          console.log("resData: " + JSON.stringify( res.data));
          userData = res.data;

          profile.fillUserProfileInfo(userData);

          userService.getKeks(userData.nickName)
            .then(function (res) {
              res.data.forEach(function (el) {
                profile.userDetails.keks.push({
                  text: el.text,
                  date: el.createDate,
                  owner_id: userData.nickName,
                  owner_nickName: userData.nickName,
                  owner_avatar: userData.avatarUrl,
                  kek_image: el.imageUrl,
                  reactions:{ loys: 10, disloys: 5},
                  comments: userService.getCommentsOfKek(el.commentsDto),
                  showComments: false
                });
              });
              console.log(res.data);
            })
            .catch(function (err) {
              console.log(err);
            })
        })
        .catch(function (err) {
          console.log(err);
        });
    }
  }
</script>
<style>



</style>
