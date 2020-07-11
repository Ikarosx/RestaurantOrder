import VueRouter from "vue-router";
import Vue from "vue";
import routes from "@/router";
Vue.use(VueRouter);
const router = new VueRouter({
  routes: routes,
  // mode: "history"
});

router.beforeEach((to, from, next) => {
  //!***********身份校验***************
  var user = window.localStorage.getItem("user");
  if (to.path == "/") {
    next({ path: "/menu" });
  } else if (user != null && to.path == "/login") {
    next({ path: "/menu" });
  } else if (user != null || to.path == "/login") {
    next();
  } else {
    next({ path: "/login" });
  }
});
// 授权
// router.afterEach((to, from, next) => {
//   if (openAuthorize) {
//     let activeUser;
//     try {
//       activeUser = utilApi.getActiveUser();
//     } catch (e) {
//       //alert(e)
//     }
//     if (activeUser) {
//       //权限校验
//       let requiresAuth = to.meta.requiresAuth;
//       let permission = to.meta.permission;
//       if (requiresAuth && permission) {
//         utilApi.checkAuthorities(router, permission);
//       }
//     }
//   }
// });

import axios from "axios";
import * as loginApi from "@/base/api/login";
// 添加请求拦截器
// axios.interceptors.request.use(
//   (config) => {
//     // 在发送请求向header添加jwt
//     let token = localStorage.getItem("access_token");
//     if (token) {
//       config.headers["Authorization"] = "bearer " + token;
//     }
//     return config;
//   },
//   (error) => {
//     return Promise.reject(error);
//   }
// );
// 响应拦截
axios.interceptors.response.use((data) => {
  console.log("data=");
  console.log(data);
  if (data && data.data) {
    console.log(data.data.code);
    if (data.data.code == "11002") {
      console.log("重新登录")
      window.localStorage.removeItem('user');
      router.push('/login')
    } else if (data.data.code && data.data.code == "401") {
    
    }
  }
  return data;
});

export default router;
