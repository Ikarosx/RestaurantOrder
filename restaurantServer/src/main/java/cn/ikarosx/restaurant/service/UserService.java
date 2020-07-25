package cn.ikarosx.restaurant.service;

import cn.ikarosx.restaurant.entity.User;
import cn.ikarosx.restaurant.entity.query.UserQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface UserService {
  ResponseResult insertUser(User user);

  ResponseResult deleteUserById(String id);

  ResponseResult updateUser(User user);

  ResponseResult getUserById(String id);

  ResponseResult listUsersByPage(int page, int size, UserQueryParam userQueryParam);

  ResponseResult listAllUsers();

  ResponseResult login(String username, String password);
    
    ResponseResult logout();
}
