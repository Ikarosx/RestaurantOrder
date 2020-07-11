import http from "./public";
import qs from "qs";
var apiUrl = "http://127.0.0.1";

export const listAllMenuType = () => {
  return http.requestQuickGet(apiUrl + "/menu/type/all");
};

export const listMenuByPage = (page, size, param) => {
  return http.requestQuickGet(
    apiUrl + "/menu/" + page + "/" + size + "?" + qs.stringify(param)
  );
};

export const listAllMenu = () => {
  return http.requestQuickGet(apiUrl + "/menu/all");
};

export const generateOrder = (order) => {
  return http.requestPost(apiUrl + "/order", order);
};

export const getOrderById = (orderId) => {
  return http.requestQuickGet(apiUrl + "/order/" + orderId);
};
