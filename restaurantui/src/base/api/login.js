import http from "./public";
import qs from "qs"
var apiUrl = 'http://127.0.0.1'

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
