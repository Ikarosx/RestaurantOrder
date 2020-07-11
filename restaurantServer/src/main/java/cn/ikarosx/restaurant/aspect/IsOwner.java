package cn.ikarosx.restaurant.aspect;

import cn.ikarosx.restaurant.entity.User;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface IsOwner {
  /** ID的位置 */
  int position() default 0;
  /** 是什么的ID */
  Class clazz() default User.class;
  /** ID字段的名字，驼峰写法 */
  String name() default "Id";
  /** 主键的类型 */
  Class idClazz() default String.class;
}
