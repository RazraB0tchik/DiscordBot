<script>
import stomp_connection from "../tools/stomp_connection";
import {defineComponent} from "vue";
import {createRouter as $router} from "vue-router";

export default defineComponent({
  methods: {
    getUrl(){
      stomp_connection.methods.send_discord_auth_request();
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
