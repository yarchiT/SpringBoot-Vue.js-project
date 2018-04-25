<template>
  <div class="user">

    <router-view></router-view>

  </div>

</template>

<script>
  // import axios from 'axios';
  // import {APIENDPOINT} from  '../../http-common.js';
  import adminService from './userService.js';
  export default {
    name: "user",
    data() {
      return {
        loginDetails : {
          nickName : '',
          password : ''
        },
      }
    },
    methods: {

    }
  }

</script>


<style>
  .user {
    padding: 2em 2rem;
  }

  h1{
    color: purple;
    padding: 1rem 0rem;
  }

  user__container p {
    font-size: 32px;
    color: purple;
  }
</style>
