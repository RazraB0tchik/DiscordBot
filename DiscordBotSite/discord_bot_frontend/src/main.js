import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router'
import connector from './tools/stomp_connection'

connector.methods.connect()

const app = createApp(App)

app.use(router)

app.mount('#app')