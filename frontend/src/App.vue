<template>
  <div id="app">
    <Navigation></Navigation>
    <router-view/>
  </div>
</template>

<script>
  import Navigation from './components/Navigation'
export default {
  name: 'App',
  components: {
    'Navigation': Navigation,
  }
}
</script>

<style>
#app {
   font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}
</style>
