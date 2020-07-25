package cn.ikarosx.restaurant.controller.impl;

import cn.ikarosx.restaurant.aspect.IsOwner;
import cn.ikarosx.restaurant.aspect.NeedAdmin;
import cn.ikarosx.restaurant.controller.UserController;
import cn.ikarosx.restaurant.entity.User;
import cn.ikarosx.restaurant.entity.jpa.Insert;
import cn.ikarosx.restaurant.entity.jpa.Update;
import cn.ikarosx.restaurant.entity.query.UserQueryParam;
import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserControllerImpl implements UserController {
  @Autowired private UserService userService;

  @Override
  @PostMapping("/login")
  @ApiOperation("用户登录")
  public ResponseResult login(String username, String password) {
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      return CommonCodeEnum.INVALID_PARAM;
    }
    return userService.login(username, password);
  }

  @Override
  @GetMapping("/logout")
  @ApiOperation("用户注销")
  public ResponseResult logout() {
    return userService.logout();
  }

  @Override
  @PostMapping
  @ApiOperation("增加用户，默认是普通用户")
  public ResponseResult insertUser(@Validated(Insert.class) @RequestBody User user) {
    return userService.insertUser(user);
  }

  @Override
  @DeleteMapping("/{id}")
  @ApiOperation("删除用户")
  @IsOwner
  public ResponseResult deleteUserById(@PathVariable String id) {
    return userService.deleteUserById(id);
  }

  @Override
  @PutMapping("/{id}")
  @ApiOperation("更新用户")
  @IsOwner
  public ResponseResult updateUser(
      @PathVariable String id, @Validated(value = Update.class) @RequestBody User user) {
    user.setId(id);
    return userService.updateUser(user);
  }

  @Override
  @GetMapping("/{id}")
  @IsOwner
  public ResponseResult getUserById(@PathVariable String id) {
    return userService.getUserById(id);
  }

  @Override
  @GetMapping("/{page}/{size}")
  @NeedAdmin
  public ResponseResult listUsersByPage(
      @PathVariable int page, @PathVariable int size, UserQueryParam userQueryParam) {
    if (page < 1) {
      page = 1;
    }
    if (size <= 0) {
      size = 10;
    }
    if (userQueryParam == null) {
      userQueryParam = new UserQueryParam();
    }
    return userService.listUsersByPage(page, size, userQueryParam);
  }

  @Override
  @GetMapping
  @NeedAdmin
  public ResponseResult listAllUsers() {
    return userService.listAllUsers();
  }
}
