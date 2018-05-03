<template xmlns:v-for="http://www.w3.org/1999/xhtml">
  <div style="margin-top:80px">
    <h1 v-if="noUsersFound">No users found</h1>
    <ul>

      <li style="width:750px" v-for="user in foundUsers">
        <a v-bind:href="/profile/ + user.nickName">
        <img v-bind:src="getImgUrl(user.avatarUrl)" >
        <h1>{{user.firstName}} {{user.lastName}}</h1>
        <h3>{{user.nickName}}</h3>
        <p>{{user.bio}}</p>
        </a>
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
    watch: {
      '$route.query.nickname'() {
        this.fetchUsers();
      }
    },
    methods: {
      getImgUrl(pet) {
        if(!pet || /^\s*$/.test(pet)){
          pet = "empty.png"
        }
        var images = require.context('../assets');
        return images('./' + pet )
      },
      fetchUsers:function()
          {
            axios.get(APIENDPOINT + '/search?nickname=' + this.$route.query.nickname).then(response=>{
              this.foundUsers = response.data;
            }).catch(err=>{
              console.log('error fetching searched users : ' + err)
            })
          },
    },
      created()
      {
        this.fetchUsers();
      }
    }
</script>

<style scoped>
  * {margin: 0; padding: 0;}

  div {
    margin: 20px;
  }

  li a {
    margin: 0px;
    display: block;
    width: 100%;
    height: 100%;
    color: inherit;
    text-decoration: none;
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
