
import {uuid} from "vue-uuid";
import axios from "axios";

const baseUrl = "http://localhost:7500"

const api = axios.create(
    {baseURL: baseUrl,
    withCredentials: true});

let responseDataFromLink = [];
let responseDataFromDiscordCode = [];

export default {

    data() {
        return {
            csrf_token: null,
            headerName: null,
            state: null
        }
    },
    methods: {
        async send_code_youtube(code){
           await api.post(  "auth/youtube_redirect", {
                'code' : code
            }).then(response => {
                console.log(response)
            })
        },

       async send_discord_auth_request(){
             localStorage.state = uuid.v4();
             window.location.href= "https://discord.com/api/oauth2/authorize?client_id=1124629066344046674&redirect_uri=http%3A%2F%2Flocalhost%3A5173%2Fauthorize_discord&response_type=code&scope=identify&state="+ localStorage.state;
       },

      async get_csrf_token(){
            await api.get("/auth/get_csrf").then(response => {
                this.csrf_token = response.data.getHeader("X-Csrf-Token");
            })
      },

     async update_access_token(){
       await api.put("/auth/update_access",
           {
               withCredentials: true,
               headers:{
                   "SameSite": "None"
               }
           })
           .then(response => {
               responseDataFromDiscordCode = response.data;
               localStorage.access = responseDataFromDiscordCode.access_token;
           })
     },

       async send_code_discord(code, state_user){
            if (localStorage.state === state_user && localStorage.state!==null) {
              await api.post("/auth/discord_redirect", {
                    'code': code,
                    'fingerprint': localStorage.user_print
                },
                  ).then(response => {
                   responseDataFromDiscordCode = response.data;
                   localStorage.access = responseDataFromDiscordCode.access_token;
                  });
            }
            else
                alert("Please try authorize fully :(")
            },
        },
}