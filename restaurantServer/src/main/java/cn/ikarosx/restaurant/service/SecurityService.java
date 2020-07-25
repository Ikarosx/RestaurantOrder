package cn.ikarosx.restaurant.service;

/**
 * @author Ikarosx
 * @date 2020/7/25 9:34
 */
public interface SecurityService {
  boolean orderIdAuth(String orderId);
}
