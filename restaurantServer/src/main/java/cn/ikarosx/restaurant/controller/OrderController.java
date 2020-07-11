package cn.ikarosx.restaurant.controller;

import cn.ikarosx.restaurant.entity.Order;
import cn.ikarosx.restaurant.entity.controller.PostOrder;
import cn.ikarosx.restaurant.entity.param.OrderQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface OrderController {

  ResponseResult insertOrder(PostOrder order);
  
  ResponseResult payOrder(String orderId);

  ResponseResult deleteOrderById(String id);

  ResponseResult updateOrder(String id, Order order);

  ResponseResult getOrderById(String id);

  ResponseResult listOrdersByPage(int page, int size, OrderQueryParam orderQueryParam);

  ResponseResult listAllOrders();
}
