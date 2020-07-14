package cn.ikarosx.restaurant.aspect;

import java.lang.annotation.*;

/**
 * @author Ikaros
 * @date 2020/07/10 09:15
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminAccess {}
