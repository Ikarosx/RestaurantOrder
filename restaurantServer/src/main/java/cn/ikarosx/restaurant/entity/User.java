package cn.ikarosx.restaurant.entity;

import cn.ikarosx.restaurant.entity.jpa.Insert;
import cn.ikarosx.restaurant.entity.jpa.Update;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Ikarosx
 * @date 2020/7/7 20:48
 */
@Data
@Entity
@Table
@GenericGenerator(name = "uuid", strategy = "uuid")
@EntityListeners(value = AuditingEntityListener.class)
public class User {
  @Id
  @GeneratedValue(generator = "uuid")
  @NotNull(groups = Update.class)
  private String id;

  @NotEmpty(groups = {Insert.class})
  private String username;

  @NotEmpty(groups = {Insert.class})
  private String password;

  @Min(0)
  @Max(1)
  @Value("0")
  private int type;

  @CreatedDate private Date createTime;
  @LastModifiedDate private Date updateTime;
}
