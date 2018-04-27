<template>
  <div style="margin-bottom: 40px;">
      <nav class="navbar"  v-on:click.prevent>
        <router-link class="logo" v-bind:key="0"
                     :to="`/`">Kekker</router-link>

        <div class="wrap" v-if="this.$store.state.isLoggedIn">
          <div class="search">
            <input type="text" v-model="searchInput" class="searchTerm" placeholder="What are you looking for?">
            <button v-on:click="search" class="searchButton">
              <span>Search</span>
            </button>
          </div>
        </div>
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
          <a class="spacing" v-on:click="logout()">{{text}} </a>
        </div>
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
          },
          {
            id: 2,
            text: 'Sign up',
            page:'/signup'
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
        text: 'Log out',
        searchInput: ''
      }
    },
    methods: {
      logout: function () {
        this.$store.state.isLoggedIn = false;
          window.localStorage.clear();
        this.$router.push("/")
      },
      search: function() {
        if (this.searchInput)
          this.$router.push({path: '/search', query: {nickname: this.searchInput}});
      }
    }
  }
</script>

<style>
  @import '../assets/styles/toastr.min.css';
  @import url(https://fonts.googleapis.com/css?family=Open+Sans);

  .search {
    width: 100%;
    position: relative
  }

  .searchTerm {
    float: left;
    width: 100%;
    border: 3px solid #00B4CC;
    padding: 5px;
    height: 20px;
    border-radius: 5px;
    outline: none;
    color: #9DBFAF;
  }

  .searchTerm:focus{
    color: #00B4CC;
  }

  .searchButton {
    position: absolute;
    right: -95px;
    width: 79px;
    height: 36px;
    border: 1px solid #00B4CC;
    background: #00B4CC;
    text-align: center;
    color: #fff;
    border-radius: 5px;
    cursor: pointer;
    font-size: 20px;
  }

  /*Resize the wrap to see the search bar change!*/
  .wrap {
    position: absolute;
    margin-left: 90px;
    margin-top: 7px;
  }
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
    z-index: 500;
  }

  nav a{
    display:inline-block;
    padding: 18px 30px;
    color:#fff !important;
    font-weight:bold;
    font-size:14px;
    text-decoration:none !important;
    line-height:1;
    cursor: pointer;
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
