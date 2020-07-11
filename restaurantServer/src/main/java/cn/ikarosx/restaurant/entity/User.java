package cn.ikarosx.restaurant.entity;

import cn.ikarosx.restaurant.entity.jpa.Insert;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

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
  private String id;

  @NotEmpty(groups = {Insert.class})
  private String username;

  @NotEmpty(groups = {Insert.class})
  private String password;

  @CreatedDate private LocalDateTime createTime;
  @LastModifiedDate private LocalDateTime updateTime;
}
