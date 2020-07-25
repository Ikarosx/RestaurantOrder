package cn.ikarosx.restaurant.service;

import cn.ikarosx.restaurant.entity.Order;
import cn.ikarosx.restaurant.entity.controller.PostOrder;
import cn.ikarosx.restaurant.entity.query.OrderQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface OrderService {
  ResponseResult insertOrder(PostOrder order);

  ResponseResult deleteOrderById(String id);

  ResponseResult updateOrder(Order order);

  ResponseResult getOrderById(String id);

  ResponseResult listOrdersByPage(int page, int size, OrderQueryParam orderQueryParam);

  ResponseResult listAllOrders(OrderQueryParam orderQueryParam);
  
  ResponseResult payOrder(String orderId);
}
