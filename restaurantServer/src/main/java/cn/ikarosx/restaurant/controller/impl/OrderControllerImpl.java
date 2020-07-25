package cn.ikarosx.restaurant.controller.impl;

import cn.ikarosx.restaurant.aspect.IsOwner;
import cn.ikarosx.restaurant.aspect.PreAuthorize;
import cn.ikarosx.restaurant.controller.OrderController;
import cn.ikarosx.restaurant.entity.Order;
import cn.ikarosx.restaurant.entity.controller.PostOrder;
import cn.ikarosx.restaurant.entity.query.OrderQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@RestController
@RequestMapping("order")
public class OrderControllerImpl implements OrderController {
  @Autowired private OrderService orderService;

  @Override
  @PostMapping
  @IsOwner(clazz = PostOrder.class, name = "UserId")
  public ResponseResult insertOrder(@Validated @RequestBody PostOrder order) {
    return orderService.insertOrder(order);
  }

  @Override
  @GetMapping("/{orderId}/pay")
  public ResponseResult payOrder(@PathVariable String orderId) {
    return orderService.payOrder(orderId);
  }

  @Override
  @DeleteMapping("/{id}")
  @PreAuthorize("@securityServiceImpl.orderIdAuth(#id)")
  public ResponseResult deleteOrderById(@PathVariable String id) {
    return orderService.deleteOrderById(id);
  }

  @Override
  @PutMapping("/{id}")
  @PreAuthorize("@securityServiceImpl.orderIdAuth(#id)")
  public ResponseResult updateOrder(@PathVariable String id, @Validated @RequestBody Order order) {
    return orderService.updateOrder(order);
  }

  @Override
  @GetMapping("/{id}")
  @PreAuthorize("@securityServiceImpl.orderIdAuth(#id)")
  public ResponseResult getOrderById(@PathVariable String id) {
    return orderService.getOrderById(id);
  }

  @Override
  @GetMapping("/{page}/{size}")
  @IsOwner(position = 2, clazz = OrderQueryParam.class, name = "UserId")
  public ResponseResult listOrdersByPage(
      @PathVariable int page, @PathVariable int size, OrderQueryParam orderQueryParam) {
    if (page < 1) {
      page = 1;
    }
    if (size <= 0) {
      size = 10;
    }
    if (orderQueryParam == null) {
      orderQueryParam = new OrderQueryParam();
    }
    return orderService.listOrdersByPage(page, size, orderQueryParam);
  }

  @Override
  @GetMapping
  @IsOwner(clazz = OrderQueryParam.class, name = "UserId")
  public ResponseResult listAllOrders(OrderQueryParam orderQueryParam) {
    return orderService.listAllOrders(orderQueryParam);
  }
}
