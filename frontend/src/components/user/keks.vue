<template>
  <div>
    <div class="kek" v-for="kek in keks" v-bind:data-owner="kek.owner_id">
      <div class="w3-container w3-card w3-white" >
        <img :src="kek.owner_avatar" class="kek_owner_avatar">
        <h2 class="w3-text-grey w3-padding-16 kek_owner_nickname">{{kek.owner_nickName}}</h2>
        <input type="image" :src="getImgUrl('delete-button.svg')" class="remove_kek_btn"/>
        <hr>
        <div class="w3-container">
          <h5 class="w3-opacity">{{kek.kek_image}}</h5>
          <p>{{kek.text}}</p>
          <hr>

          <div class="reactions">
            <div class="icon_middle"><img :src="getLoysImg()" align="middle" class="reaction_icons"><span>{{kek.reactions.loys}}</span></div>
            <div class="icon_middle"><img :src="getDisLoysImg()" align="middle" class="reaction_icons"><span>{{kek.reactions.disloys}}</span></div>
          </div>
          <hr>
          <button class="comments_btn" v-on:click="toggle">Comments</button>

          <div class="comments_area"  v-show="showComment">
            <Comments v-bind:comments="kek.comments"></Comments>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Comments from './comments.vue'
  export default {
    name: 'keks', //this is the name of the component

    data(){
        return{
            showComment:false
        }
    },

    props:{
        keks:{
            type: Array,
            required: true
        }
    },

    components:{
      'Comments': Comments
    },

    methods:{

      getImgUrl(pet) {
        var images = require.context('../../assets/');
        return images('./' + pet )
      },
      toggle(){
        this.showComment = !this.showComment;
        if(this.showComment){
          document.getElementsByClassName("comments_btn").style.position = 'inherit';
        }else{
          document.getElementsByClassName("comments_btn").style.position = 'absolute';
        }
      },
      getLoysImg(){
        return this.getImgUrl("loys.svg")
      },

      getDisLoysImg(){
        return this.getImgUrl("disloys.svg")
      },
      getUserKeks: function () {

      },
      created() {

          /*do smth on load*/
      }

    }
  }
</script>
<style>

  .w3-container{
    margin-top: 10px;
    position:relative;
  }

  .w3-container .kek_owner_avatar{
    position: absolute;
    top: 3%;
    left: 3%;
  }


  .kek_owner_avatar{
    width: 8%;
    height: auto;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    display:inline;
  }

  .kek_owner_nickname{
    display:inline;
    white-space:nowrap;

  }

  .remove_kek_btn{
    display:inline;
    white-space:nowrap;
    width: 4%;
    height: auto;
  }

  .w3-container .remove_kek_btn{
    position: absolute;
    top: 3%;
    right: 3%;
  }

  .w3-container .reactions{
    position: absolute;
    right: 3%;
    bottom: 10%;
  }

  .icon_middle {
    width:10px;
    float: right;
    height: 15px;
  }
  .icon_middle span {
    display:block;
  }

  .w3-container .comments_btn{
    position: absolute;
    left: 3%;
    bottom: 3%;
  }

  .comments_area{
    bottom: 3%;
  }

</style>
