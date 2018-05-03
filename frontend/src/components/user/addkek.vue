<template>
  <div class="clearfix">
    <form>
      <textarea v-model="newKekText"></textarea>
    </form>
    <button class="post-btn add-btn" v-bind:class="{'disabled-btn': isButtonDisabled }" :disabled="isButtonDisabled" v-on:click="addKek">Add kek</button>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'AddKek',
    data () {
      return {
        isAddRequestPending: false,
        newKekText: ""
      }
    },
    computed :{
      isButtonDisabled : function()
      {
        return this.isAddRequestPending || this.newKekText === '';
      }
    },
    methods: {
      addKek:function()
      {
        console.log('sending kek...')
        var kekData = {
          text: this.newKekText
        };
        this.newKekText = "";
        this.isAddRequestPending = true;
        //todo:post request instead of get to our server; emit event to parent and update view
        axios.post(`http://jsonplaceholder.typicode.com/posts`)
          .then(response => {
            // JSON responses are automatically parsed.
            console.log('dummy request success')
            //this.followersList = response.data;
          })
          .catch(e => {
            console.log('err ' + e);
          }).then(()=>{
            this.isAddRequestPending = false;
        })
      }
    }

  }
</script>

<style>
  textarea {
    width: 100%;
    height: 150px;
    padding: 12px 20px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: #f8f8f8;
    font-size: 16px;
    resize: none;
  }
  .clearfix::after {
    content: "";
    display: block;
    clear: both;
  }
  .post-btn {
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    background-color: #4CAF50; /* Green */
    float:right;
  }
  .disabled-btn {
    background-color: #4caf50b5;
  }
</style>
