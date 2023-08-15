import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router'
import stomp_connection from "@/tools/stomp_connection";


const app = createApp(App)
stomp_connection.methods.connect();
app.use(router)

app.mount('#app')