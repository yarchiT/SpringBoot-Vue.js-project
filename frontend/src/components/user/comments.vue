<template>
  <div class="comment-list">
    <ul>
      <li v-for="comment in comments" transition="slide" v-bind:data-owner="comment.owner_id">
        <div class="profile">
          <img :src="getImgUrl(comment.owner_avatar)" alt="">
        </div>
        <div class="msg"><div class="msg-body">
          <p class="name">{{comment.owner_nickname }}<span class="date">{{comment.date}}</span></p>
          <p>{{comment.text}}</p>
        </div></div>
      </li>
    </ul>

  </div>
</template>

<script>
  export default{
      name:'comments',


      props:{
          comments:{
              type:Array,
              required: true
          }
      },

      methods:{
        getImgUrl(pet) {
          var images = require.context('../../assets/')
          return images('./' + pet )
        }
      }
  }
</script>

<style>
  .comment-list .profile img {
    border-radius: 50%;
    border: 2px solid #fff;
    width: 48px;
    height: 48px;
    background-color: #ddd;
    box-shadow: 0 0 5px #ddd;
  }

  .comment-list{
    overflow: auto;
    width: 90%;
    height: 100px;
  }

  @media (max-width: 600px) {
    .brand img {
      width: 80px;
    }
  }

  comment-list li {
    list-style: none;
    text-align: left;
    overflow: hidden;
    margin-bottom: 2em;
    padding: .4em;
  }

  .comment-list .profile {
    width: 80px;
    float: left;
  }

  .comment-list .msg {
    width: 86%;
    float: left;
    background-color: #fff;
    border-radius: 0 5px 5px 5px;
    box-shadow: 0 4px 8px -2px rgba(0,0,0,.05);
    position: relative;
  }

  .comment-list .msg-body {
    padding: .8em;
    color: #666;
    line-height: 1.5;
  }

  .comment-list .name {
    margin: 0;
    color: #999;
    font-weight: 700;
    font-size: .8em;
  }

  p {
    display: block;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
  }

  .comment-list .date {
    float: right;
  }


</style>
