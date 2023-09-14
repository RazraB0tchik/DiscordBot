// import axios from "axios";
//
// const app = axios.create({
//     baseURL: "http://localhost:7500/**"
// })
//
// let tokenCsrf = null;
//
// app.interceptors.request.use(
//     configs => {
//         console.log("sad")
//         if(configs.headers.get("Connection") === "upgrade"){
//             configs.headers = {'X-CSRF-TOKEN': tokenCsrf};
//         }
//     }
// )
//
// app.interceptors.response.use(
//     configs=>{
//         console.log("aasd")
//         if(configs.headers.get("Connection") === "keep-alive"){
//             tokenCsrf = configs.headers.get("X-CSRF-TOKEN");
//             console.log(tokenCsrf);
//         }
//     }
// )