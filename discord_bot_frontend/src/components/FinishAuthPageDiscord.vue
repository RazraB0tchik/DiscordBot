<script>
import connection from "../tools/controller";

export default {
  data() {
    return {
      user_code: "",
      state: ""
    }
  },
  methods: {
    send_code: function (){
      this.user_code = this.$route.query.code;
      this.state = this.$route.query.state;
      console.log(this.user_code + " " + this.state)
      connection.methods.send_code_discord(this.user_code, this.state);
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