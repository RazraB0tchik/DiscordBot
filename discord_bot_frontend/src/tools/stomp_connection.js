import {Stomp, Client, CompatClient} from "@stomp/stompjs";
import discord_secret from "/home/makar/IdeaProjects/Bots/DiscordBot/src/main/resources/client_secret_discord.json"
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
    mounted() {
      localStorage.state_user = null;
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

      send_code_youtube: function (code){
          this.client_connected.send("/app/youtubeAuthRedirect", {}, JSON.stringify({'code' : code}));
      },

        send_discord_auth_request: function (){
          let state = Math.random().toString(30);
          localStorage.state_user=state;
          console.log(discord_secret.redirect_url)
          let urlDiscordAuth = "https://discord.com/oauth2/authorize?response_type=code"+"&client_id="+ discord_secret.client_id +"&scope=identify&state="+state+"&redirect_uri="+discord_secret.redirect_url;
            window.location.href = urlDiscordAuth;
      },

        send_code_discord: function (code, state_user){
          if (localStorage.state_user === state_user && localStorage.state_user!==null) {
              this.client_connected.send("/app/discordAuthRedirect", {}, JSON.stringify({'code': code}));
              localStorage.clear();
          }
          else
              alert("Please try authorize fully :(")
        },
    }
}