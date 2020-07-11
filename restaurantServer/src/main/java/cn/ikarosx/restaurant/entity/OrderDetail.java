package cn.ikarosx.restaurant.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ikarosx
 * @date 2020/7/7 21:24
 */
@Data
@Entity
@Table
@GenericGenerator(name = "uuid", strategy = "uuid")
@EntityListeners(value = AuditingEntityListener.class)
public class OrderDetail {
  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private String orderId;
  private String menuId;
  private Double price;
  private Integer num;
  private Double sum;
  @CreatedDate private Date createTime;
  @LastModifiedDate private Date updateTime;
}
