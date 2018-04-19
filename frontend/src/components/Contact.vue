<template>
  <div>
    <h1>Contact Me</h1>
    <ul>
      <li>Email: {{email}}</li>
      <li>Website: {{web}}</li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: 'Contact',
    data(){
      return {
        email: "ytykhonchuk.gmail.com",
        web: "dsf"
      }
    }
  }
</script>
