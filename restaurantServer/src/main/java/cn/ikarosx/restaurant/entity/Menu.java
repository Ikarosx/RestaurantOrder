package cn.ikarosx.restaurant.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单
 *
 * @author Ikarosx
 * @date 2020/7/7 20:15
 */
@Data
@Entity
@Table
@GenericGenerator(name = "uuid", strategy = "uuid")
public class Menu {
  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private String name;
  private Double price;
  private String type;
}
