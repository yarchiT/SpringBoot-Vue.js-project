<template>
  <div>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <div class="w3-quarter">
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
          <div class="w3-container" style="padding: 0;">
            <a v-bind:href="/users/ + user.nickName"  v-for="user in followingUsers">
              <div class="follower-div">
                <img  v-bind:src="user.avatarUrl"class="follower-icon fa-briefcase w3-margin-right">
                <span class="following-user-name">{{user.nickName}}</span>
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'following-users-sidebar', // this is the name of the component,
    props:{
      followingUsers: {
        type: Array,
        required: true
      }
    }
  }
</script>

<style>
  .follower-div{
    padding-left: 10px;
    text-align: left;
    padding-top: 5px;
    padding-bottom: 5px;
  }
  .follower-div:hover {
    background-color: aqua;
  }
  .follower-icon {
    width: 3em;
  }
  .following-user-name {
    text-decoration: none;
  }

  html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
