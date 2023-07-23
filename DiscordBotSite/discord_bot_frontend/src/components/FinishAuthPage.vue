<script>
import stomp_connection from "@/tools/stomp_connection";
import axios from "axios";
export default {
  computed: {
    stomp_connection() {
      return stomp_connection
    }
  },
  data(){
    return{
      userId: ""
    }
  },
  // updated() {
  //   this.getParams();
  //   console.log(this.userId + " :update")
  // },

  methods: {
    send_userId(){
      this.userId = this.$route.query.code;
      stomp_connection.methods.send_message(this.userId)
    }
  }
}
</script>

<template>
<div style="width: 500px; height: 500px; background-color: aqua">
  Success, you authorize!
  <button v-on:click=send_userId()>Click</button>
</div>
</template>

<style scoped>

</style>