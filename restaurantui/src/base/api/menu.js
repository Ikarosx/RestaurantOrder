import http from "./public";
import qs from "qs";
import { systemConfig } from "@/base/config/system";
var apiUrl = systemConfig.apiUrl;

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

export const listAllOrderByUserId = (userId) => {
  return http.requestQuickGet(apiUrl + "/order?userId=" + userId);
};

export const listOrderDetailsByOrderId = (orderId) => {
  return http.requestQuickGet(apiUrl + "/order/detail?orderId=" + orderId);
};
export const insertMenu = (menu) => {
  return http.requestPost(apiUrl + "/menu/", menu);
};

export const insertMenuType = (menuType) => {
  return http.requestPost(apiUrl + "/menu/type", menuType);
};
