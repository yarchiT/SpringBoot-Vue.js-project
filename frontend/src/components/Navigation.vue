<template>
  <div>
      <nav class="navbar"  v-on:click.prevent>
        <router-link class="logo" v-bind:key="0"
                     :to="`/`">Kekker</router-link>

       <!-- <a class="logo" href="/"><img src="../assets/logo.svg"/></a>-->
        <div v-if="!this.$store.state.isLoggedIn" class="home-nav link-on-right">
          <router-link class="spacing" v-for="routes in homeLinks"
                       v-bind:key="routes.id"
                       :to="`${routes.page}`">{{routes.text}}</router-link>
        </div>

        <div v-else-if="this.$store.state.isLoggedIn" class="user-nav link-on-right">
          <router-link class="spacing" v-for="routes in userLinks"
                       v-bind:key="routes.id"
                       :to="`${routes.page}`">{{routes.text}}</router-link>
          <a v-on:click.prevent="logout()">{{text}}</a></div>
      </nav>
  </div>
</template>

<script>
  export default {
    name: 'Navigation', // this is the name of the component
    data() {
      return {
        homeLinks: [
          {
            id: 0,
            text: 'About',
            page:'/about'
          },
          {
            id: 1,
            text: 'Login',
            page:'/login'
          }
        ],
        userLinks:[
          {
            id: 0,
            text: 'Timeline',
            page:'/timeline'
          },
          {
            id: 1,
            text: 'Profile',
            page:'/profile'
          }
        ],
        text: 'Log out'
      }
    },
    methods: {
      logout: function () {
        this.$store.state.isLoggedIn = false;
        window.localStorage.clear();
        this.$router.push("/")
      }
    }
  }
</script>

<style>
  .logo{
    float: left;
  }
  .link-on-right{
    float: right;
  }
  nav{
    overflow: hidden;
    background-color:#5597b4;
    box-shadow:0 1px 1px #ccc;
    border-radius:2px;
    overflow: hidden;
    position: fixed; /* Set the navbar to fixed position */
    top: 0; /* Position the navbar at the top of the page */
    width: 100%; /* Full width */
  }

  nav a{
    display:inline-block;
    padding: 18px 30px;
    color:#fff !important;
    font-weight:bold;
    font-size:14px;
    text-decoration:none !important;
    line-height:1;
  /*  background-color:transparent;

    -webkit-transition:background-color 0.25s;
    -moz-transition:background-color 0.25s;
    transition:background-color 0.25s;*/
  }


  /* Change background on mouse-over */
  .nav a:hover {
    background: #ddd;
    color: black;
  }


</style>
