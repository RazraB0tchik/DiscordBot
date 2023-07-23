<script>
import connector from "../tools/stomp_connection"
export default {
  methods: {
    sendTest: function (){
      connector.methods.send_message()
    }
  }
}

</script>

<template>
  <div> ABOBA
    <button v-on:click="sendTest">Click me!</button>
  </div>
</template>

<style scoped>

</style>
