package cn.ikarosx.restaurant.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ikarosx
 * @date 2020/7/7 21:24
 */
@Data
@Entity
@Table
@GenericGenerator(name = "uuid", strategy = "uuid")
public class OrderDetail {
  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private String orderId;
  private String menuId;
  private Integer num;
  private Double sum;
}
