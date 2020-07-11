package cn.ikarosx.restaurant.dao;

import cn.ikarosx.restaurant.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {}
