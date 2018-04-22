<template>
  <div>
    <following-users-sidebar v-bind:following-users="followingUsers"></following-users-sidebar>
    <p>Motherfuckers timeline</p>
  </div>
</template>

<script>
  import FollowingUsersSidebar from "../followingUsersSidebar/followingUsersSidebar";
  import axios from 'axios';

  export default {
    components: {FollowingUsersSidebar},
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
        ]
      }
    },
    created() {
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
