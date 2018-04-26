<template>
  <div>
    <div class="w3-row-padding">

      <!-- Left Column -->
      <div class="w3-third" id="followers">
        <following-users-sidebar v-bind:following-users="followingUsers"></following-users-sidebar>
      </div><!--</div>  End Left Column-->

      <div class="w3-twothird">
        <Keks v-bind:keks="keks" v-bind:isTimeline="true"></Keks>
      </div>
    </div>
  </div>
</template>

<script>
  import FollowingUsersSidebar from "../followingUsersSidebar/followingUsersSidebar";
  import Keks from './keks.vue';
  import axios from 'axios';

  export default {


    components: {
      FollowingUsersSidebar,
      Keks
    },
    name: 'timeline', //this is the name of the component,
    data() {
      return {
        followingUsers: [
          {
            nickName: 'slava',
            avatarUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHjp65ReAyvAqAZv-AEAQDFh-v9eiOcit3a98sC5ME-uyKo_7qkA'
          },
          {
            nickName: 'max',
            avatarUrl: 'https://www.w3schools.com/w3images/avatar2.png'
          },
          {
            nickName: 'yarik',
            avatarUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHjp65ReAyvAqAZv-AEAQDFh-v9eiOcit3a98sC5ME-uyKo_7qkA'
          }
        ],
        keks:[
          {
            id:"ke4ara1",
            text:"I was in Boston and I forgot Lenin..Upsss!",
            date:"12/03/2019",
            owner_id:"kimasik",
            owner_nickName:"kimasik",
            owner_avatar:"user.jpg",
            kek_image:"../../assets/kekPhoto.jpg",
            reactions:{loys:24,disloys:3},
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
            reactions:{loys:43,disloys:13},
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
            reactions:{loys:143,disloys:43},
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
          },


          {
            id:"maxon",
            text:"Where is my money bitch?",
            date:"12/03/2019",
            owner_id:"kimasik",
            owner_nickName:"kimasik",
            owner_avatar:"user.jpg",
            kek_image:"../../assets/kekPhoto.jpg",
            reactions:{loys:24,disloys:3},
            comments:[
              {
                id:"bolka",
                owner_id:"kimano",
                owner_avatar:"trump.jpeg",
                owner_nickname:"i_love_ivanka",
                text:"Shmall!",
                date:"12/03/2001"
              },
              {
                id:"bylka"  ,
                owner_id:"kimano",
                owner_avatar:"trump.jpeg",
                owner_nickname:"i_love_ivanka",
                text:"pisdosiki",
                date:"22/03/2017"
              }
            ],
            showComments:false
          },
          {
            id:"robot",
            text:"Kiss my mac",
            date:"15/03/2019",
            owner_id:"kimasik",
            owner_nickName:"kimasik",
            owner_avatar:"user.jpg",
            kek_image:"../../assets/kekPhoto.jpg",
            reactions:{loys:43,disloys:13},
            comments:[
              {
                id:"boiler",
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
            id:"shnaps",
            text:"Shaurmishkkaaaaa",
            date:"05/04/2019",
            owner_id:"kimasik",
            owner_nickName:"kimasik",
            owner_avatar:"user.jpg",
            kek_image:"../../assets/kekPhoto.jpg",
            reactions:{loys:143,disloys:43},
            comments:[
              {
                id:"maven",
                owner_id:"kimano",
                owner_avatar:"user.jpg",
                owner_nickname:"kimasik",
                text:"Drestaty!",
                date:"12/12/2012"
              }
            ],
            showComments:false
          }
        ],
      }
    },

    methods:{
      fillKeks: function () {

      },

    },

    created() {
      //todo: send request to our server instead of this dummy
      axios.get(`http://jsonplaceholder.typicode.com/posts`)
        .then(response => {
          // JSON responses are automatically parsed.
          console.log('fetch followers data')
          //this.followersList = response.data;
        })
        .catch(e => {
          console.log('err');
        })

    }
  }
</script>
<style>


</style>
