<script>
import axios from "axios";
export default {
  data(){
    return{
      userId: ""
    }
  },
  updated() {
    this.getParams();
  },
  methods: {
    getParams(){
      this.userId = this.$route.query.code;
    },
    async sendRequest(){
      axios.get("http://localhost:8000/user/getUserId", {
        "userId": this.userId
      })
          .then(response => {console.log(response + "ok")})
          .catch(e => console.log(e))
    }
  }
}
</script>

<template>
<div style="width: 500px; height: 500px; background-color: aqua">
  Success, you authorize!
  <button v-on:click="this.sendRequest()">Click</button>
</div>
</template>

<style scoped>

</style>