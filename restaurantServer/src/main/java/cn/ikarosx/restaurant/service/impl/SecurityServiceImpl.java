package cn.ikarosx.restaurant.service.impl;

import cn.ikarosx.restaurant.dao.OrderRepository;
import cn.ikarosx.restaurant.entity.Order;
import cn.ikarosx.restaurant.service.SecurityService;
import cn.ikarosx.restaurant.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ikarosx
 * @date 2020/7/25 9:34
 */
@Service
public class SecurityServiceImpl implements SecurityService {
  @Autowired private OrderRepository orderRepository;
  /**
   * 判断该订单是否属于当前登录用户
   *
   * @param orderId
   * @return
   */
  @Override
  public boolean orderIdAuth(String orderId) {
    Optional<Order> orderOptional = orderRepository.findById(orderId);
    Order order = orderOptional.orElse(null);
    return order != null && StringUtils.equals(order.getUserId(), SessionUtils.getId());
  }
}
