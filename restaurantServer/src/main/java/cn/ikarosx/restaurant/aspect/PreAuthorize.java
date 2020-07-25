package cn.ikarosx.restaurant.aspect;

import java.lang.annotation.*;

/**
 * @author Ikarosx
 * @date 2020/7/25 9:36
 */
@Target(value = ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthorize {
  String value();
}
