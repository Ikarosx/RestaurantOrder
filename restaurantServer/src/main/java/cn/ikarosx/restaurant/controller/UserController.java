package cn.ikarosx.restaurant.controller;

import cn.ikarosx.restaurant.entity.User;
import cn.ikarosx.restaurant.entity.param.UserQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface UserController {

  ResponseResult login(String username, String password);

  ResponseResult logout();

  ResponseResult insertUser(User user);

  ResponseResult deleteUserById(String id);

  ResponseResult updateUser(String id, User user);

  ResponseResult getUserById(String id);

  ResponseResult listUsersByPage(int page, int size, UserQueryParam userQueryParam);

  ResponseResult listAllUsers();
}
