import SockJS from "sockjs-client"
import {Client, Stomp} from "@stomp/stompjs"

const connected_status = false;
const ws_connected = {
    connect: () => connected_status,
    disconnect: () => connected_status
}
const web_socket_url = "http://localhost:8080/music_giga_bot";
export default {
    data:{
      return: {
          client_connected: null
      }
    },
    methods: {
        connect: function (){
          const socket = new SockJS(web_socket_url);
          ws_connected.stomp_connected = Stomp.over(socket);
          this.client_connected = ws_connected.stomp_connected;
          this.client_connected.connect({}, frame => {
              alert("Connected " + frame)
          })
        },
        send_message: function (message) {
            console.log(message)
            this.client_connected.send("/app/hello", {},  JSON.stringify({'userId': message}))
        }
    }
}