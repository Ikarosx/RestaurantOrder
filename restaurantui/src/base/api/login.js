import http from "./public";
import qs from "qs"
import { systemConfig }  from '@/base/config/system'
var apiUrl = systemConfig.apiUrl

export const login = params => {
  return http.requestPostForm(apiUrl + "/user/login", qs.stringify(params));
};

export const logout =() => {
  return http.requestQuickGet(apiUrl + "/user/logout");
};
export const register = params => {
  return http.requestPost(apiUrl + "/user", params);
};

export const getAccessToken = () => {
  return http.requestGet(adminApiUrl + "/oauth/jwt");
};
