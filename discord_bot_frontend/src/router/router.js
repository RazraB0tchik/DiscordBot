import {createRouter, createWebHistory} from "vue-router";
import Main from "../components/MainPage.vue"
import FinishYoutube from "../components/FinishAuthPageYoutube.vue"
import FinishDiscord from "../components/FinishAuthPageDiscord.vue"

const routes = [
    {path: "/", component: Main, name: "Main"},
    {path: "/authorize_youtube", component: FinishYoutube, name: "AuthYoutube"},
    {path: "/authorize_discord", component: FinishDiscord, name: "AuthDiscord"}
]

const router = createRouter({history: createWebHistory(), routes});
export default router;