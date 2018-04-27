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
              <button class="editProfileBtn"  v-show="true" @click="edit" >Edit</button> <!--todo:show if nickname == authorizated user-->
            </div>
          </div><br>

          <!-- End Left Column -->
        </div>
        <!--Right column-->
        <div class="w3-twothird">
          <div class="clearfix">
            <form>
              <textarea v-model="newKekText"></textarea>
            </form>
            <button class="post-btn add-btn" v-bind:class="{'disabled-btn': isButtonDisabled }" :disabled="isButtonDisabled" v-on:click="addKek">Add kek</button>
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
            newKekText: ""
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

      addKek:function()
      {
        console.log('sending kek...')
        var kekData = {
          text: this.newKekText
        };
        this.newKekText = "";
        this.isAddRequestPending = true;
        //todo: change url

        axios.post("http://jsonplaceholder.typicode.com/posts", {
          params: {
            kekText: kekData.text
          }
        })
          .then(response => {
            // JSON responses are automatically parsed.
            console.log('dummy request success')
            //this.followersList = response.data;
          })
          .catch(e => {
            console.log('err ' + e);
          }).then(()=>{
          this.isAddRequestPending = false;
        })
      },

      fillInfo: function () {
        this.userDetails.nickname="kimasik",
          this.userDetails.firtstName="kim",
          this.userDetails.lastName="chin-in",
          this.userDetails.gender="male",
          this.userDetails.email="kim4in@rambler.com",
          this.userDetails.avatar="user.jpg",
          this.userDetails.bio="I'm fucking bomberman, I will get married with your handsome dog. Please fuck mexican man and follow me!"
        this.userDetails.keks = [
          {
            id:"ke4ara1",
            text:"I was in Boston and I forgot Lenin..Upsss!",
            date:"12/03/2019",
            owner_id:"kimasik",
            owner_nickName:"kimasik",
            owner_avatar:"user.jpg",
            kek_image:"../../assets/kekPhoto.jpg",
            reactions:[
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"kimano"
                }
              },
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"bracho"
                }
              },
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"pancho"
                }
              },
              {
                type:"disloys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"rancho"
                }
              },
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"lol"
                }
              }
            ],
            comments:[
              {
                id:"lol",
                owner_id:"kimano",
                owner_avatar:"trump.jpeg",
                owner_nickname:"i_love_ivanka",
                text:"Wow..get mann bro",
                date:"12/03/2001"
              },
              {
                id:"kek"  ,
                owner_id:"kimano",
                owner_avatar:"trump.jpeg",
                owner_nickname:"i_love_ivanka",
                text:"Make America great again!",
                date:"22/03/2017"
              }
            ],
            showComments:false
          },
          {
            id:"lol1",
            text:"Ohhh curly brackets, where is my spiner?",
            date:"15/03/2019",
            owner_id:"kimasik",
            owner_nickName:"kimasik",
            owner_avatar:"user.jpg",
            kek_image:"../../assets/kekPhoto.jpg",
            reactions:[
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"nerty"
                }
              },
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"bracho"
                }
              },
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"pancho"
                }
              },
              {
                type:"disloys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"rancho"
                }
              },
              {
                type:"disloys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"tekity"
                }
              }
            ],
            comments:[
              {
                id:"4ebyrek",
                owner_id:"kimano",
                owner_avatar:"user.jpg",
                owner_nickname:"kimasik",
                text:"Smoke weeds every day bro!",
                date:"12/12/2012"
              }
            ],
            showComments:false
          },
          {
            id:"ke4ara2",
            text:"'Ebat` Kapat`'-told me father and showed his dick to my sister",
            date:"05/04/2019",
            owner_id:"kimasik",
            owner_nickName:"kimasik",
            owner_avatar:"user.jpg",
            kek_image:"../../assets/kekPhoto.jpg",
            reactions:[
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"kimano"
                }
              },
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"bracho"
                }
              },
              {
                type:"loys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"pancho"
                }
              },
              {
                type:"disloys",
                owner:{
                  avatarUrl: "xyu",
                  nickName:"rancho"
                }
              }
            ],
            comments:[
              {
                id:"snoopydog",
                owner_id:"kimano",
                owner_avatar:"user.jpg",
                owner_nickname:"kimasik",
                text:"Swim!Dream!Love! And fuck bro!",
                date:"12/12/2012"
              }
            ],
            showComments:false
          }
        ]
      },

    },
    created() {
      var profileUsersNickName = this.$route.params.nickname;

      if (profileUsersNickName == null) {
        var currentUser = JSON.parse(window.localStorage.getItem('lbUser'));
        profileUsersNickName = currentUser.nickName;
        if (currentUser == null)
          return;
      }

      var userData = {};
      var profile = this;
      //this.fillInfo();
      userService.getUserInfo(profileUsersNickName)
        .then(function (res) {
          console.log("resData: " + JSON.stringify(res.data));
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
                  reactions: {loys: 10, disloys: 5},
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
  .disabled-btn {
    background-color: #4caf50b5;
  }


</style>
