# RestaurantOrder
基于SpringBoot和Vue的餐馆点餐系统，单机版

## QuickStart
### 后端

1. 预先准备mysql，数据库名称为restaurant
- restaurant.sql

2. 修改配置文件数据库账号密码
`src/main/resources/application-template.yaml`

3. 运行RestaurantApplication.java

### 前端

```shell
# npm/yarn安装依赖
npm install
# 运行
npm run serve
# 编译
npm run build
```
### 访问
http://localhost:8080/

默认账号密码
1. 管理员 Peggy : 123456
2. 普通用户 Ikaros : 123456

管理员多了可以添加菜单的功能

## 功能说明-后端
### 接口文档
采用Swagger2,启动后访问127.0.0.1/swagger-ui.html,默认端口80

### 数据库

mysql

使用SpringJPA交互

### 身份校验

登陆：取出数据库用户，对上传的密码进行MD5加密，比较是否相同

Session进行身份标识，默认30m过期

### 权限校验

用三个注解配合Aspect使用

#### IsOwner

标识参数中的UserId是否与当前登录用户一致

#### NeedAdmin

是否需要管理员权限，管理员的type为1，普通用户为0

#### PreAuthorize

SpEL表达式，可以自定义自己的权限验证方法，用于复杂校验

### 异常统一处理

用ControllerAdvice拦截自定义异常

错误代码都存放在CommonCodeEnum

### Docker

默认不开启打包成Docker，如果要开启，在pom.xml下`dockerfile-maven-plugin`插件中取消注释`<goal>build</goal>`

DockerFile中以`openjdk:8-jdk-alpine`为基础镜像以减少打包后的体积

请自行修改pom.xml中docker相关参数，比如镜像名称与标签

```shell
docker run --name restaurant -p 8888:80 -d --restart=always 镜像名称
```



## 功能说明-前端

前端不是很熟悉，这里就简单介绍一下



### 优化

cdn

### 拦截器

axios设置拦截器拦截响应，如果session过期则重新登录

### 配置

#### API

`src/base/config/system.js`里的apiUrl

所有的请求是基于这个apiUrl来拼接的



![image-20200725160228211](https://ikaros-picture.oss-cn-shenzhen.aliyuncs.com/typora/Ikaros/image-20200725160228211.png)

![image-20200725160333009](https://ikaros-picture.oss-cn-shenzhen.aliyuncs.com/typora/Ikaros/image-20200725160333009.png)