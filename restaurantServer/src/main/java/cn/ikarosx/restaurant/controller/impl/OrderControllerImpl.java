package cn.ikarosx.restaurant.controller.impl;

import cn.ikarosx.restaurant.controller.OrderController;
import cn.ikarosx.restaurant.entity.Order;
import cn.ikarosx.restaurant.entity.controller.PostOrder;
import cn.ikarosx.restaurant.entity.param.OrderQueryParam;
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
  public ResponseResult deleteOrderById(@PathVariable String id) {
    return orderService.deleteOrderById(id);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseResult updateOrder(@PathVariable String id, @Validated @RequestBody Order order) {
    return orderService.updateOrder(order);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseResult getOrderById(@PathVariable String id) {
    return orderService.getOrderById(id);
  }

  @Override
  @GetMapping("/{page}/{size}")
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
  public ResponseResult listAllOrders() {
    return orderService.listAllOrders();
  }
}
