<template>
  <div class="container">
    <form onsubmit="submit()">
      <div class="row">
        <div class="col-25">
          <label for="src">Image</label>
        </div>
        <div class="col-75">
          <input id="src" type="file" v-on:change="handleFileUpload" accept="image/*"/>
          <img v-bind:src="imagePreview" v-show="showPreview"/>
        </div>
      </div>

      <div class="row">

        <div class="col-25">
          <label for="fnickname">Nickname</label>
        </div>
        <div class="col-75">
          <input type="text" id="fnickname" name="nickname" placeholder="Your nickname.." v-model="user.nickName">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="firstname">First Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="firstname" name="firstname" placeholder="Your first name name.."  v-model="user.firstname">
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="lastname">Last Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="lastname" name="lastname" placeholder="Your last name name.."  v-model="user.lastname">
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="lemail">Email</label>
        </div>
        <div class="col-75">
          <input type="text" id="lemail" name="email" placeholder="Your email.." v-model="user.email">
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="country">Gender</label>
        </div>
        <div class="col-75">
          <select id="country" v-model="user.gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="about">About</label>
        </div>
        <div class="col-75">
          <textarea id="about" placeholder="Write something about yourself.." style="height:200px" v-model="user.about"></textarea>
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Edit">
      </div>
    </form>
  </div>
</template>

<script>

  export default {
    name: 'edit' ,// this is the name of the component
    data(){
      return {
        file: '',
        showPreview: false,
        imagePreview: '',
        user:{
          nickName:"",
          firstname:"",
          lastname:"",
          email:"",
          gender:"",
          about:""
        }
      }

    },

    methods: {

      handleFileUpload(){
        /*
          Set the local file variable to what the user has selected.
        */
        this.file = this.$refs.file.files[0];

        /*
          Initialize a File Reader object
        */
        let reader  = new FileReader();

        /*
          Add an event listener to the reader that when the file
          has been loaded, we flag the show preview as true and set the
          image to be what was read from the reader.
        */
        reader.addEventListener("load", function () {
          this.showPreview = true;
          this.imagePreview = reader.result;
        }.bind(this), false);

        /*
          Check to see if the file is not empty.
        */
        if( this.file ){
          /*
            Ensure the file is an image file.
          */
          if ( /\.(jpe?g|png|gif)$/i.test( this.file.name ) ) {
            /*
              Fire the readAsDataURL method which will read the file in and
              upon completion fire a 'load' event which we will listen to and
              display the image in the preview.
            */
            reader.readAsDataURL( this.file );
          }
        }
      },
      submit(){

        let formData = new FormData();
        formData.append('avatar', this.file);
        formData.append('nickName',this.user.nickName);
        formData.append('fullName', this.user.fullName);
        formData.append('email',this.user.email);
        formData.append('gender',this.user.gender);
        formData.append('about',this.user.about);

        axios.post( '/file-preview',//change here
          formData,
          {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }
        ).then(function(){
            this.$swal('', 'Succssefully edited!', 'OK');
        }).catch(function(){
          this.$swal('', 'Failure in editing', 'OK');
        });
      }
    }
  }


</script>
<style>
  * {
    box-sizing: border-box;
  }

  input[type=text], select, textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
  }

  label {
    padding: 12px 12px 12px 0;
    display: inline-block;
  }

  input[type=submit] {
    margin-top: 2%;
    background-color: dodgerblue;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
  }

  input[type=submit]:hover {
    background-color: dodgerblue;
  }

  .container {
    margin-top: 3%;
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
  }

  .col-25 {
    float: left;
    width: 25%;
    margin-top: 6px;
  }

  .col-75 {
    float: left;
    width: 75%;
    margin-top: 6px;
  }

  /* Clear floats after the columns */
  .row:after {
    content: "";
    display: table;
    clear: both;
  }

  img {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
    width: 150px;
  }

  img:hover {
    box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
  }

  /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
  @media screen and (max-width: 600px) {
    .col-25, .col-75, input[type=submit] {
      width: 100%;
      margin-top: 0;
    }
  }

</style>
