import {createRouter, createWebHistory} from "vue-router";
import Main from "../components/MainPage.vue"
import Finish from "../components/FinishAuthPage.vue"

const routes = [
    {path: "/", component: Main, name: "Main"},
    {path: "/authorize", component: Finish, name: "Auth"}
]

const router = createRouter({history: createWebHistory(), routes});
export default router;