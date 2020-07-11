package cn.ikarosx.restaurant.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜系类型
 *
 * @author Ikarosx
 * @date 2020/7/7 20:45
 */
@Data
@Entity
@Table
@GenericGenerator(name = "uuid", strategy = "uuid")
public class MenuType {
  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private String name;
}
