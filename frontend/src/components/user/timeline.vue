<template>
  <div>
    <div class="w3-row-padding">

      <!-- Left Column -->
      <div class="w3-third" id="followers">
        <h2>People you follow:</h2>
        <following-users-sidebar v-bind:following-users="followingUsers"></following-users-sidebar>
      </div><!--</div>  End Left Column-->

      <div class="w3-twothird">
        <h2>Check out last keks</h2>
        <Keks v-bind:keks="keks" v-bind:isTimeline="true"></Keks>
      </div>
    </div>
  </div>
</template>

<script>
  import FollowingUsersSidebar from "../followingUsersSidebar/followingUsersSidebar";
  import Keks from './keks.vue';
  import axios from 'axios';
  import userService from './userService';

  export default {


    components: {
      FollowingUsersSidebar,
      Keks
    },
    name: 'timeline', //this is the name of the component,
    data() {
      return {
        followingUsers: [],
        keks:[],
      }
    },

    methods:{
    },

    created() {

      var timeline = this;
      var currentUserNickName = this.$store.state.getNickName();

      userService.getUserFollowings(currentUserNickName)
        .then(function (res) {
           timeline.followingUsers = res.data;
            userService.getTimelinekeks(currentUserNickName)
              .then(function (res) {
                    res.data.forEach(function (el) {
                      timeline.keks.push({
                        text: el.text,
                        date: el.createDate,
                        owner_id: el.ownerNickName,
                        owner_nickName: el.ownerNickName,
                        owner_avatar: el.ownerAvatarUrl,
                        kek_image: el.imageUrl,
                        reactions: userService.getReactionsOfKek(el.reactionsDto),
                        comments: userService.getCommentsOfKek(el.commentsDto),
                        showComments: false
                      });
                    })
              })
              .catch(function (err) {
                console.log(err);
              })
        })
        .catch(function (err) {
          console.log("Get user followings: "+err)
        })

    }
  }
</script>
<style>


</style>
