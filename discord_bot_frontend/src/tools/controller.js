
import discord_secret from "/home/makar/IdeaProjects/Bots/DiscordBot/src/main/resources/client_secret_discord.json"
import getBrowserFingerprint from "get-browser-fingerprint";
import axios from "axios";

const baseUrl = "http://localhost:7500"

const api = axios.create(
    {baseURL: baseUrl});

export default {

    data() {
        return {
            client_connected: null,
            token: null,
            headerName: null,
            state: null
        }
    },
    methods: {
        async send_code_youtube(code){
           await api.post(  "auth/youtubeAuthRedirect", {
                'code' : code
            }).then(response => {
                console.log(response)
            })
        },


        send_discord_auth_request: function (){
            let state = Math.random().toString(30);
            localStorage.state=state;
            let urlDiscordAuth = "https://discord.com/oauth2/authorize?response_type=code"+"&client_id="+ discord_secret.client_id +"&scope=identify&state="+state+"&redirect_uri="+discord_secret.redirect_url;
            window.location.href = urlDiscordAuth;
        },

       async send_code_discord(code, state_user){
            if (localStorage.state === state_user && localStorage.state!==null) {
              await api.post("/auth/discordAuthRedirect", {
                    'code': code,
                    'fingerprint': localStorage.user_print
                },
                  ).then(response => {
                    console.log(response)
                    localStorage.state = null;
                })
            }
            else
                alert("Please try authorize fully :(")
            },
        },
}