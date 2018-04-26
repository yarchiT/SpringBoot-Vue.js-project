<template xmlns:v-for="http://www.w3.org/1999/xhtml">
  <div style="margin-top:80px">
    <h1 v-if="noUsersFound">No users found</h1>
    <ul>
      <li style="width:750px" v-for="user in foundUsers">
        <img v-bind:src="user.avatarUrl" >
        <h1>{{user.firstName}} {{user.lastName}}</h1>
        <h3>{{user.nickName}}</h3>
        <p>{{user.bio}}</p>
      </li>



    </ul>
  </div>
</template>

<script>
  import axios from 'axios';
  import {APIENDPOINT} from "../app.config";

  export default {
        name: "search-result",
    data()
    {
      return {
        foundUsers: []
      }
    },
    computed: {
          noUsersFound: function()
          {
            return this.foundUsers.length ===0;
          }
    },
    route: {
      canReuse: false,
    },
      created()
      {
        console.log('send request ' + this.$route.query.nickname)
        axios.get(APIENDPOINT + '/search?nickname=' + this.$route.query.nickname).then(e=>{

          this.foundUsers = e.data;
        }).catch(err=>{
        })
      }
    }
</script>

<style scoped>
  * {margin: 0; padding: 0;}

  div {
    margin: 20px;
  }

  ul {
    list-style-type: none;
    width: 500px;
  }

  h3 {
    color: #271c1c73;
  }
  img {
    width: 180px;
    height: 180px;
  }

  li img {
    float: left;
    margin: 0 15px 0 0;
  }

  li {
    padding: 10px;
    overflow: auto;
  }

  li:hover {
    background: #eee;
    cursor: pointer;
  }
</style>
