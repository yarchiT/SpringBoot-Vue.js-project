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
              <img :src="getImgUrl(userDetails.avatar)" style="width:100%" alt="Avatar">
              <div class="w3-display-bottomleft w3-container w3-text-black">
                <h2>{{userDetails.nickname}}</h2>
              </div>
            </div>
            <div class="w3-container">
              <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>{{userDetails.firtstName + " " + userDetails.lastName}}</p>
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
          <Keks v-bind:keks="userDetails.keks"></Keks>
          <!--add timeline here-->

        </div>
     </div>
  </div>


  </div>
</template>

<script>
  import Keks from './keks.vue';
  export default {
    name: 'profile', //this is the name of the component
    data(){
        return{
            userDetails:{
                nickname:'',
                firtstName: '',
                lastName:'',
                email:'',
                avatar:'',
                gender:'',
                bio:'',
                keks:''
            }
        }
    },
    components:{
        'Keks': Keks
    },
    methods:{
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
              text:"I was in Boston and I forgot Lenin..Upsss!",
              date:"12/03/2019",
              owner_id:"kimasik",
              owner_nickName:"kimasik",
              owner_avatar:"user.jpg",
              kek_image:"../../assets/kekPhoto.jpg",
              reactions:{loys:24,disloys:3}
            },
            {
              text:"Ohhh curly brackets, where is my spiner?",
              date:"15/03/2019",
              owner_id:"kimasik",
              owner_nickName:"kimasik",
              owner_avatar:"user.jpg",
              kek_image:"../../assets/kekPhoto.jpg",
              reactions:{loys:43,disloys:13}
            },
            {
              text:"'Ebat` Kapat`'-told me father and showed his dick to my sister",
              date:"05/04/2019",
              owner_id:"kimasik",
              owner_nickName:"kimasik",
              owner_avatar:"user.jpg",
              kek_image:"../../assets/kekPhoto.jpg",
              reactions:{loys:143,disloys:43}
            }
          ]
        },
      getImgUrl(pet) {
        var images = require.context('../../assets/')
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
      this.fillInfo();
    }
  }
</script>
<style>



</style>
