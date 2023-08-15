import {Stomp, Client, CompatClient} from "@stomp/stompjs";
import SockJS from "sockjs-client"

const connect_status = false;
const ws_connected = {
    connect: () => connect_status,
    disconnect: () => connect_status
}

const web_socket_url = "http://localhost:7500/music_bot";
export default {

    data() {
        return {
            client_connected: null
        }
    },
    methods: {
      connect: function (){
          const socket = new SockJS(web_socket_url);
          ws_connected.stomp_connected = Stomp.over(socket);
          this.client_connected = ws_connected.stomp_connected;
          this.client_connected.connect({}, frame => {
              alert("Connected + frame")
          })
      },

      send_message: function (message){
          console.log(message);
          this.client_connected.send("/app/getAuth", {}, JSON.stringify({'code' : message}));
      }
    }
}