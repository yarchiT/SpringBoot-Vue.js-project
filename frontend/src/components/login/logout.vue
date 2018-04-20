
<script>


  export default {
    name: "login",
    data() {
      return {
        loginDetails : {
          residentID : '',
          password : ''
        },
        errorMsg: ''
      }
    },
    methods: {
      logout:function () {
        var app = this;
        app.$store.state.isLoggedIn = false;
       window.localStorage.setItem('lbUser', null);

          app.$router.push('/login');

      }
    },
    created:function() {
      this.logout();
    }
  }
</script>

