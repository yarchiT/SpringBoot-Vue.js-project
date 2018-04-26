<template>
  <div>
    <div class="kek" v-for="kek in keks" v-bind:data-owner="kek.owner_id" v-bind:id="kek.id">
      <div class="w3-container w3-card w3-white" >
        <img :src="kek.owner_avatar" class="kek_owner_avatar">
        <h2 class="w3-text-grey w3-padding-16 kek_owner_nickname">{{kek.owner_nickName}}</h2>
        <input v-if="!isTimeline" type="image" :src="getImgUrl('delete-button.svg')" class="remove_kek_btn" @click="deleteKek(kek)"/>
        <hr>
        <div class="w3-container">
          <h5 class="w3-opacity">{{kek.kek_image}}</h5>
          <p>{{kek.text}}</p>
          <hr>

          <div class="reactions">
            <div class="container-fluid">

              <section id="feedback">
                <div class="row centered">
                  <div class="col-xs-6" id="like">
                    <a href="#" style="color: dodgerblue">{{countReactions(kek,"loys")}} Loys</a>
                  </div>
                  <div class="col-xs-6" id="dislike">
                    <a href="#" style="color: dodgerblue">{{countReactions(kek,"disloys")}} Disloys</a>
                  </div>
                </div>
              </section>
            </div>
            </div>
          <br>
          <button class="comments_btn" v-on:click="toggle(kek)" v-bind:data-kekId="kek.id">Comments</button>
        </div>
        <transition name="slide-fade">
        <div class="comments_area"  v-show="kek.showComment" v-bind:data-kekId="kek.id" v-bind:class="{ borderForComments : kek.showComment }">
          <Comments v-bind:comments="kek.comments"></Comments>
        </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
  import Comments from './comments.vue'
  export default {
    name: 'keks', //this is the name of the component

    data () {
      return {
        loys: 0,
        disloys: 0
      }
    },

    computed: {
      isDisabled() {
        // evaluate whatever you need to determine disabled here...
        return this.form.validated;
      }
    },

    props:{
        keks:{
            type: Array,
            required: true
        },
        isTimeline:{
          type:Boolean,
          required:false
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
		toggle(kek){
        this.$set(kek,'showComment',!kek.showComment);
      },
      getLoysImg(){
        return this.getImgUrl("loys.svg")
      },

      getDisLoysImg(){
        return this.getImgUrl("disloys.svg")
      },
      getUserKeks: function () {

      },

      deleteKek(kek){
        console.log("delete comment");
        let removeIndex = this.keks.map(function(item) { return item.id; }).indexOf(kek.id);
        this.keks.splice(removeIndex, 1);
        let isDeleted = true;
        // let isDeleted = false;
        //
        // axios.post('/api/deleteKek',{
        //   params:{
        //     kekId:kek.id
        //   }
        //
        // }).then(response => isDeleted = response.data)
        //   .catch(function (error) {
        //     console.log(error);
        //   });
        //
        if(isDeleted){
          alert("Deleted successfully!")
        }


      },

      countReactions(kek,reactionType){
        const result = kek.reactions.filter(reaction => reaction.type === reactionType).length;
        return result;
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
    width:20px;
    float: right;
    height: 15px;
  }
  .icon_middle span {
    display:block;
  }

  .w3-container .comments_btn{
    position: absolute;
    left: 3%;
    bottom: 8%;
  }

  .comments_area{
    margin-top: 4%;
    margin-bottom: 4%;
    bottom: 3%;
    border-color: dodgerblue;
  }

  .kek{
    height: 50%;
  }

  .comments_btn{
    background:none;
    border:none;
    margin:0;
    padding:0;
    color: dodgerblue;
    outline:none;

  }

  .comments_btn:hover{
    background: #eee;
  }

  .borderForComments{
    border-style: solid;
    border-width: thin;
  }

  .container-fluid{
    width: 200px;
  }


  .slide-fade-enter-active {
    transition: all .3s ease;
  }
  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slide-fade-enter, .slide-fade-leave-to
  {
    transform: translateX(10px);
    opacity: 0;
  }
</style>
