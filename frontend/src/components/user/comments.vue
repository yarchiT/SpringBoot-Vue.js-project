<template>


  <div class="comment-list">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <div class="row row-comment" v-for="comment in comments" transition="slide" v-bind:data-owner="comment.owner_id">
      <div class="col-sm-1">
        <div class="thumbnail">
          <img class="img-responsive user-photo" :src="getImgUrl(comment.owner_avatar)">
        </div><!-- /thumbnail -->
      </div><!-- /col-sm-1 -->

      <div class="col-sm-5">
        <div class="panel panel-default">
          <div class="panel-heading">
            <strong>{{comment.owner_nickname }}</strong> <span class="text-muted">{{comment.date}}</span>
          </div>
          <div class="panel-body">
            {{comment.text}}
          </div><!-- /panel-body -->
        </div><!-- /panel panel-default -->
      </div><!-- /col-sm-5 -->
    </div>
  <hr>
    <form>
      <textarea v-model="newCommentText" style="width: 90%"></textarea>
    </form>
    <button class="post-btn add-btn" v-bind:class="{'disabled-btn': isButtonDisabled }" :disabled="isButtonDisabled" v-on:click="addComent">Add comment</button>


  </div>
</template>

<script>
  import userService from "./userService";
  export default{
      name:'comments',
    data(){
      return {
        newCommentText: '',
        currentUserNickName: ''
      }
      },
      props:{
          comments:{
              type:Array,
              required: true
          },
        kekId:{
            type: Number,
            required: true
        }

      },
    computed :{
      isButtonDisabled : function()
      {
        return this.newKekText === '';
      }
    },

      methods:{
        getImgUrl(pet) {

          if(!pet || /^\s*$/.test(pet)){
            pet = "empty.png"
          }
          var images = require.context('../../assets/');
          return images('./' + pet )
        },
        addComent(){
          var comments = this;
          if(this.newCommentText !== ''){
            userService.addComment(this.kekId, this.newCommentText, this.currentUserNickName)
              .then(function (res) {
                console.log(JSON.stringify(res.data));
                comments.comments.push({
                  text: res.data.text,
                  date: res.data.creationDate,
                  owner_id: res.data.owner.nickName,
                  owner_avatar: res.data.owner.avatarUrl
                });
              })
              .catch(function (err) {
                console.log(err);
              });
            comments.newCommentText = '';
          }
        }
      },
    created() {
    this.currentUserNickName = this.$store.state.getNickName();
    }
  }
</script>

<style>
  .post-btn {
    border: none;
    color: white;
    padding: 5px 12px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    background-color: #4CAF50; /* Green */
  }

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
    height: 150px;
    overflow-x: hidden;
  }

  @media (max-width: 600px) {
    .brand img {
      width: 80px;
    }
  }

  /*comment-list li {
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
*/
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

  .thumbnail {
    padding:0px;
  }
  .panel {
    position:relative;
  }
  .panel>.panel-heading:after,.panel>.panel-heading:before{
    position:absolute;
    top:11px;left:-16px;
    right:100%;
    width:0;
    height:0;
    display:block;
    content:" ";
    border-color:transparent;
    border-style:solid solid outset;
    pointer-events:none;
  }
  .panel>.panel-heading:after{
    border-width:7px;
    border-right-color:#f7f7f7;
    margin-top:1px;
    margin-left:2px;
  }
  .panel>.panel-heading:before{
    border-right-color:#ddd;
    border-width:8px;
  }


  .row-comment{
    width: 200%;
  }

</style>
