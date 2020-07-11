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
 * @date 2020/7/7 20:49
 */
@Data
@Entity
@Table(name = "orders")
@GenericGenerator(name = "uuid", strategy = "uuid")
@EntityListeners(value = AuditingEntityListener.class)
public class Order {
  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private String userId;
  private Double sum;
  private Integer status;
  @CreatedDate private Date createTime;
  @LastModifiedDate private Date updateTime;
}
