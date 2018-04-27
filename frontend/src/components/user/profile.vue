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

              <hr>
              <button class="editProfileBtn"  v-show="isOwnProfile" @click="edit" >Edit</button> <!--todo:show if nickname == authorizated user-->
            </div>
          </div><br>

          <!-- End Left Column -->
        </div>
        <!--Right column-->
        <div class="w3-twothird">
          <div v-show="isOwnProfile" class="clearfix">
            <p class="userToUserText" style="float: left;">You have {{keksCount}} keks</p>
            <form>
              <textarea v-model="newKekText"></textarea>
            </form>
            <button class="post-btn add-btn" v-bind:class="{'disabled-btn': isButtonDisabled }" :disabled="isButtonDisabled" v-on:click="addKek">Add kek</button>
          </div>

          <div v-show="!isOwnProfile" id="userToUserInfo" class="clearfix">
            <p class="userToUserText" style="float: left;">{{userDetails.nickName}} has {{keksCount}} keks</p>
            <div v-show="areYouFollowingHim">
              <p class="userToUserText">You are following <strong>{{userDetails.nickName}}</strong></p>
              <button class="unfollow-btn" v-on:click="unFollowPerson">- Unfollow</button>
            </div>

            <div v-show="!areYouFollowingHim">
              <p class="userToUserText">Follow <strong>{{userDetails.nickName}} on KEKKER</strong></p>
              <button class="follow-btn" v-on:click="followPerson">+ Follow</button>
            </div>
          </div>


          <Keks v-bind:keks="userDetails.keks"></Keks>
          <!--add timeline here-->

        </div>
     </div>
  </div>


  </div>
</template>

<script>
  import Keks from './keks.vue';
  import userService from './userService';
  import {APIENDPOINT} from '../../app.config';
  import AddKek from './addkek';
  import axios from 'axios';

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
            },
            isAddRequestPending: false,
            newKekText: "",
            isOwnProfile: false,
            areYouFollowingHim: false,
          // user who is logged in right now
          currentUserNickName: ''
        }
    },
    components:{
        'Keks': Keks,
        'AddKek': AddKek
    },
    computed :{
      isButtonDisabled : function()
      {
        return this.isAddRequestPending || this.newKekText === '';
      },
      keksCount: function()
      {
        return this.userDetails.keks.length;
      }
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

		  edit(){
        this.$router.push('/edit');
      },
      followPerson(){
		    var app = this;
        userService.followPerson(this.currentUserNickName, this.userDetails.nickName)
          .then(function (res) {
            if(res.data == "OK"){
              app.areYouFollowingHim = true;
              app.$swal("You just followed " + app.userDetails.nickName)
            }
          })
          .catch(function (err) {
              console.log("Follow"+err);
          });
      },
      unFollowPerson(){
        var app = this;
        userService.unfollowPerson(this.currentUserNickName, this.userDetails.nickName)
          .then(function (res) {
            if(res.data == "OK"){
              app.areYouFollowingHim = false;
              app.$swal("You unfollowed " + app.userDetails.nickName)
            }
          })
          .catch(function (err) {
            console.log("Unfollow: "+err);
          });
      },
      addKek:function()
      {
        console.log('sending kek...')
        var kekData = new FormData();
        kekData.set('text', this.newKekText);
        this.newKekText = "";
        this.isAddRequestPending = true;
        //todo: change url

        axios.post(APIENDPOINT + "/keks", kekData)
          .then(response => {
            // JSON responses are automatically parsed.
            console.log('dummy request success')
            this.userDetails.keks.push(response.data);
            //this.followersList = response.data;
          })
          .catch(e => {
            console.log('err ' + e);
          }).then(()=>{
          this.isAddRequestPending = false;
        })
      },
    },
    created() {
      var profileUsersNickName = this.$route.params.nickname;
      this.currentUserNickName = this.$store.state.getNickName();

      if (profileUsersNickName == null) {
        profileUsersNickName = this.currentUserNickName;
      }

      this.isOwnProfile = profileUsersNickName == this.currentUserNickName;

      var userData = {};
      var profile = this;
      userService.getUserInfo(profileUsersNickName)
        .then(function (res) {
          userData = res.data;

          profile.fillUserProfileInfo(userData);

          if(!profile.isOwnProfile){
            userService.findIfYouFollowUser(profile.currentUserNickName, profileUsersNickName)
              .then(function (res) {
                profile.areYouFollowingHim = res.data;
              })
              .catch(function (err) {

              });
          }

          userService.getKeks(userData.nickName)
            .then(function (res) {
              res.data.forEach(function (el) {
                profile.userDetails.keks.push({
                  id:el.id,
                  text: el.text,
                  date: el.createDate,
                  owner_id: userData.nickName,
                  owner_nickName: userData.nickName,
                  owner_avatar: userData.avatarUrl,
                  kek_image: el.imageUrl,
                  reactions: userService.getReactionsOfKek(el.reactionsDto),
                  comments: userService.getCommentsOfKek(el.commentsDto),
                  showComments: false
                });

              });
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

  .userToUserText{
    font-size: x-large;
    color: #728ba1;

  }

  .editProfileBtn{
    background-color: dodgerblue;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
  }

  textarea {
    width: 100%;
    height: 150px;
    padding: 12px 20px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: #f8f8f8;
    font-size: 16px;
    resize: none;
  }
  .clearfix::after {
    content: "";
    display: block;
    clear: both;
  }
  .post-btn {
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    background-color: #4CAF50; /* Green */
    float:right;
  }

  .follow-btn {
    border: none;
    color: white;
    padding: 5px 12px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    background-color: #3597b4;
  }

  .unfollow-btn {
    border: none;
    color: white;
    padding: 5px 12px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    background-color: #ca435a;
  }
  .disabled-btn {
    background-color: #4caf50b5;
  }


</style>
