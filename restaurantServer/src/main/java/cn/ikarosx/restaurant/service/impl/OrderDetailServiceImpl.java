package cn.ikarosx.restaurant.service.impl;

import cn.ikarosx.restaurant.dao.OrderDetailRepository;
import cn.ikarosx.restaurant.entity.OrderDetail;
import cn.ikarosx.restaurant.entity.param.OrderDetailQueryParam;
import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.exception.ExceptionCast;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.OrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@Service("OrderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

  @Autowired private OrderDetailRepository orderDetailRepository;

  @Override
  public ResponseResult insertOrderDetail(OrderDetail orderDetail) {
    orderDetailRepository.save(orderDetail);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult deleteOrderDetailById(String id) {
    orderDetailRepository.deleteById(id);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult updateOrderDetail(OrderDetail orderDetail) {
    orderDetailRepository.save(orderDetail);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult getOrderDetailById(String id) {
    Optional<OrderDetail> optional = orderDetailRepository.findById(id);
    OrderDetail orderDetail = optional.orElse(null);
    if (orderDetail == null) {
      ExceptionCast.cast(CommonCodeEnum.DATA_NOT_FOUND);
    }
    return CommonCodeEnum.SUCCESS.addData("orderDetail", orderDetail);
  }

  @Override
  public ResponseResult listOrderDetailsByPage(
      int page, int size, OrderDetailQueryParam orderDetailQueryParam) {
    OrderDetail orderDetail = new OrderDetail();
    BeanUtils.copyProperties(orderDetailQueryParam, orderDetail);
    // 筛选
    ExampleMatcher matcher =
        ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<OrderDetail> example = Example.of(orderDetail, matcher);
    // 分页
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<OrderDetail> orderDetailPage = orderDetailRepository.findAll(example, pageable);
    return CommonCodeEnum.SUCCESS
        .addData("list", orderDetailPage.getContent())
        .addData("total", orderDetailPage.getTotalElements())
        .addData("totalPage", orderDetailPage.getTotalPages());
  }

  @Override
  public ResponseResult listAllOrderDetails() {
    List<OrderDetail> list = orderDetailRepository.findAll();
    return CommonCodeEnum.SUCCESS.addData("list", list).addData("total", list.size());
  }
}
