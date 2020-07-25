package cn.ikarosx.restaurant.service.impl;

import cn.ikarosx.restaurant.dao.OrderDetailRepository;
import cn.ikarosx.restaurant.dao.OrderRepository;
import cn.ikarosx.restaurant.entity.Order;
import cn.ikarosx.restaurant.entity.OrderDetail;
import cn.ikarosx.restaurant.entity.controller.PostOrder;
import cn.ikarosx.restaurant.entity.query.OrderQueryParam;
import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.exception.ExceptionCast;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.OrderService;
import cn.ikarosx.restaurant.util.SessionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

  @Autowired private OrderRepository orderRepository;
  @Autowired private OrderDetailRepository orderDetailRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResponseResult insertOrder(PostOrder postOrder) {
    Order order = new Order();
    BeanUtils.copyProperties(postOrder, order);
    order.setStatus(0);
    order = orderRepository.save(order);
    List<OrderDetail> orderDetails = postOrder.getOrderDetails();
    for (OrderDetail orderDetail : orderDetails) {
      orderDetail.setOrderId(order.getId());
    }
    orderDetailRepository.saveAll(orderDetails);
    return CommonCodeEnum.SUCCESS.addData("orderId", order.getId());
  }

  @Override
  public ResponseResult deleteOrderById(String id) {
    Order order = new Order();
    order.setUserId(SessionUtils.getId());
    order.setId(id);
    orderRepository.delete(order);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult updateOrder(Order order) {
    order.setId(SessionUtils.getId());
    orderRepository.save(order);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult getOrderById(String id) {
    Order order = new Order();
    order.setId(id);
    order.setUserId(SessionUtils.getId());
    Optional<Order> optional = orderRepository.findOne(Example.of(order));
    order = optional.orElse(null);
    if (order == null) {
      ExceptionCast.cast(CommonCodeEnum.DATA_NOT_FOUND);
    }
    return CommonCodeEnum.SUCCESS.addData("order", order);
  }

  @Override
  public ResponseResult listOrdersByPage(int page, int size, OrderQueryParam orderQueryParam) {
    Order order = new Order();
    BeanUtils.copyProperties(orderQueryParam, order);
    // 筛选
    ExampleMatcher matcher =
        ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<Order> example = Example.of(order, matcher);
    // 分页
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<Order> orderPage = orderRepository.findAll(example, pageable);
    return CommonCodeEnum.SUCCESS.addData(
        "list",
        orderPage.getContent(),
        "total",
        orderPage.getTotalElements(),
        "totalPage",
        orderPage.getTotalPages());
  }

  @Override
  public ResponseResult listAllOrders(OrderQueryParam orderQueryParam) {
    Order order = new Order();
    BeanUtils.copyProperties(orderQueryParam, order);
    // 筛选
    ExampleMatcher matcher =
        ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<Order> example = Example.of(order, matcher);
    List<Order> list = orderRepository.findAll(example);
    return CommonCodeEnum.SUCCESS.addData("list", list, "total", list.size());
  }

  @Override
  public ResponseResult payOrder(String orderId) {
    Order order = new Order();
    order.setStatus(0);
    order.setId(orderId);
    Optional<Order> optional = orderRepository.findOne(Example.of(order));
    Order order1 = optional.orElse(null);
    if (order1 != null) {
      order1.setStatus(1);
      orderRepository.save(order1);
    }
    return CommonCodeEnum.SUCCESS.clearData();
  }
}
