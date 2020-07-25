# RestaurantOrder
基于SpringBoot和Vue的餐馆点餐系统，单机版

## 后端
### 接口文档
采用Swagger2,启动后访问127.0.0.1/swagger-ui.html,默认端口80

## 启动

预先准备mysql

运行RestaurantApplication.java

## 数据库

mysql

使用SpringJPA交互

## 身份校验

登陆：取出数据库用户，对上传的密码进行MD5加密，比较是否相同

Session进行身份标识，默认30m过期

## 权限校验

用三个注解配合Aspect使用

### IsOwner

标识参数中的UserId是否与当前登录用户一致

### NeedAdmin

是否需要管理员权限，管理员的type为1，普通用户为0

### PreAuthorize

SpEL表达式，可以自定义自己的权限验证方法，用于复杂校验

## 异常统一处理

用ControllerAdvice拦截自定义异常

错误代码都存放在CommonCodeEnum

