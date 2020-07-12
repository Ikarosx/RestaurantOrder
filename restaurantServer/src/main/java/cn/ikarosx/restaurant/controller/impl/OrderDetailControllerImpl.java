
    package cn.ikarosx.restaurant.controller.impl;
import cn.ikarosx.restaurant.controller.OrderDetailController;
import cn.ikarosx.restaurant.entity.OrderDetail;
import cn.ikarosx.restaurant.entity.param.OrderDetailQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@RestController
@RequestMapping("order/detail")
public class OrderDetailControllerImpl implements OrderDetailController {
  @Autowired private OrderDetailService orderDetailService;

  @Override
  @PostMapping
  public ResponseResult insertOrderDetail(@Validated @RequestBody OrderDetail orderDetail) {
    return orderDetailService.insertOrderDetail(orderDetail);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseResult deleteOrderDetailById(@PathVariable String id) {
    return orderDetailService.deleteOrderDetailById(id);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseResult updateOrderDetail(@PathVariable String id, @Validated @RequestBody OrderDetail orderDetail) {
    return orderDetailService.updateOrderDetail(orderDetail);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseResult getOrderDetailById(@PathVariable String id) {
    return orderDetailService.getOrderDetailById(id);
  }

  @Override
  @GetMapping("/{page}/{size}")
  public ResponseResult listOrderDetailsByPage(
          @PathVariable int page, @PathVariable int size, OrderDetailQueryParam orderDetailQueryParam) {
    if (page < 1) {
      page = 1;
    }
    if (size <= 0) {
      size = 10;
    }
    if (orderDetailQueryParam == null) {
      orderDetailQueryParam = new OrderDetailQueryParam();
    }
    return orderDetailService.listOrderDetailsByPage(page, size, orderDetailQueryParam);
  }

  @Override
  @GetMapping
  public ResponseResult listAllOrderDetails(OrderDetailQueryParam orderDetailQueryParam) {
    return orderDetailService.listAllOrderDetails(orderDetailQueryParam);
  }
}
    