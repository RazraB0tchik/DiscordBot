<script>
import connection from "../tools/controller";
import controller from "../tools/controller";

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
    },
    update: function () {
      controller.methods.update_access_token();
    },
    get_cs: function (){
      controller.methods.get_csrf_token();
    }
  }
}

</script>

<template>
<div style="width: 500px; height: 500px; background-color: aqua">
  Success, you authorize!
  <button v-on:click="send_code">Click</button>
  <button v-on:click="update">Update</button>
  <button v-on:click="get_cs">CSRF</button>
</div>
</template>

<style scoped>

</style>