// import axios from "axios";
// import controller from "@/tools/controller";
//
// const app = axios.create({
//     baseURL: "http://localhost:7500/auth/update_tokens"
// })
//
//
// app.interceptors.request.use(
//     configs => {
//        controller.methods.get_csrf_token();
//        configs.headers = {"X-CSRF-TOKEN": controller.data().csrf_token};
//     }
// )

// app.interceptors.response.use(
//     configs=>{
//         console.log("aasd")
//         if(configs.headers.get("Connection") === "keep-alive"){
//             tokenCsrf = configs.headers.get("X-CSRF-TOKEN");
//             console.log(tokenCsrf);
//         }
//     }
// )