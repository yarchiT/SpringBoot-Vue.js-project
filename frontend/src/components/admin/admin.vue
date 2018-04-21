<template>
  <div class="admin">
    <h1>AdminPage</h1>

    <div class="admin__container">
      <p>You are here because you are ADMIN </p>
    </div>

  </div>

</template>

<script>
  // import axios from 'axios';
  // import {APIENDPOINT} from  '../../http-common.js';
  import adminService from './adminService.js';
  export default {
    name: "admin",
    data() {
      return {
        loginDetails : {
          userName : '',
          password : ''
        },
      }
    },
    methods: {

    }
  }

 </script>


<style>
   .admin {
     padding: 2em 2rem;
   }


   h1{
     color: purple;
     padding: 1rem 0rem;
   }

    admin__container p {
      font-size: 32px;
      color: purple;
    }
</style>
