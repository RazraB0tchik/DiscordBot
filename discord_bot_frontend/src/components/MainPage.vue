<script>
import controller from "../tools/controller";
import {defineComponent} from "vue";
import getBrowserFingerprint from "get-browser-fingerprint";

export default defineComponent({

  methods: {
    getUrl(){
      controller.methods.send_discord_auth_request();
      localStorage.user_print = getBrowserFingerprint({enableWebgl: true});
    },

    getCsrf(){
      controller.methods.get_csrf_token();
    },


    update_a(){
      controller.methods.update_access_token();
    }
  }
})

</script>

<template>
  <div> ABOBA
    <button v-on:click="getUrl">Auth in Discord!</button>
    <button v-on:click="getCsrf">Csrf</button>
    <button v-on:click="update_a">Update</button>
  </div>
</template>

<style scoped>

</style>
