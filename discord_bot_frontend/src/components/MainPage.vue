<script>
import controller from "../tools/controller";
import {defineComponent} from "vue";
import getBrowserFingerprint from "get-browser-fingerprint";

export default defineComponent({

  methods: {
    getUrl(){
      controller.methods.send_discord_auth_request();
      localStorage.user_print = getBrowserFingerprint({enableWebgl: true});
    }
  }
})

</script>

<template>
  <div> ABOBA
    <button v-on:click="getUrl">Auth in Discord!</button>
  </div>
</template>

<style scoped>

</style>
