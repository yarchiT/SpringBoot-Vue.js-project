<template>
  <div>
      <nav class="navbar"  v-on:click.prevent>
        <router-link class="logo" v-bind:key="0"
                     :to="`/`">Kekker</router-link>
       <!-- <a class="logo" href="/"><img src="../assets/logo.svg"/></a>-->
        <div class="link-on-left">
          <router-link class="spacing" v-for="routes in links"
                       v-bind:key="routes.id"
                       :to="`${routes.page}`">{{routes.text}}</router-link>
        </div>
      </nav>
  </div>
</template>

<script>
  export default {
    name: 'Navigation', // this is the name of the component
    data() {
      return {
        links: [
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
        ]
      }
    }
  }
</script>

<style>
  .logo{
    float: left;
  }
  .link-on-left{
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
