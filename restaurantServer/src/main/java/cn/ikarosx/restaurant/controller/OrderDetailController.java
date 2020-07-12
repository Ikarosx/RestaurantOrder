package cn.ikarosx.restaurant.controller;

import cn.ikarosx.restaurant.entity.OrderDetail;
import cn.ikarosx.restaurant.entity.param.OrderDetailQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface OrderDetailController {

  ResponseResult insertOrderDetail(OrderDetail orderDetail);

  ResponseResult deleteOrderDetailById(String id);

  ResponseResult updateOrderDetail(String id, OrderDetail orderDetail);

  ResponseResult getOrderDetailById(String id);

  ResponseResult listOrderDetailsByPage(
      int page, int size, OrderDetailQueryParam orderDetailQueryParam);

  ResponseResult listAllOrderDetails(OrderDetailQueryParam orderDetailQueryParam);
}
