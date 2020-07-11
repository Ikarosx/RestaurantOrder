package cn.ikarosx.restaurant.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Ikarosx
 * @date 2020/7/7 20:49
 */
@Data
@Entity
@Table(name = "orders")
@GenericGenerator(name = "uuid", strategy = "uuid")
public class Order {
  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private String userId;
  private Double sum;
  private Integer status;
  private Date createTime;
  private Date updateTime;
}
