<script>
import stomp_connection from "../tools/stomp_connection";

export default {
  data() {
    return {
      user_code: ""
    }
  },
  methods: {
    send_code: function (){
      this.user_code = this.$route.query.code;
      stomp_connection.methods.send_message(this.user_code);
    }
  }
}

</script>

<template>
<div style="width: 500px; height: 500px; background-color: aqua">
  Success, you authorize!
  <button v-on:click="send_code">Click</button>
</div>
</template>

<style scoped>

</style>