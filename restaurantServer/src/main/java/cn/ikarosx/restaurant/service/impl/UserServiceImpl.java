package cn.ikarosx.restaurant.service.impl;

import cn.ikarosx.restaurant.dao.UserRepository;
import cn.ikarosx.restaurant.entity.User;
import cn.ikarosx.restaurant.entity.query.UserQueryParam;
import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.exception.CustomException;
import cn.ikarosx.restaurant.exception.ExceptionCast;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.UserService;
import cn.ikarosx.restaurant.util.SessionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public ResponseResult insertUser(User user) {
    if (!SessionUtils.isAdmin()) {
      // 如果不是管理员，强制用户类型为普通用户
      user.setType(0);
    }
    userRepository.save(user);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult deleteUserById(String id) {
    userRepository.deleteById(id);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult updateUser(User user) {
    userRepository.save(user);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult getUserById(String id) {
    Optional<User> optional = userRepository.findById(id);
    User user = optional.orElse(null);
    if (user == null) {
      ExceptionCast.cast(CommonCodeEnum.DATA_NOT_FOUND);
    }
    return CommonCodeEnum.SUCCESS.addData("user", user);
  }

  @Override
  public ResponseResult listUsersByPage(int page, int size, UserQueryParam userQueryParam) {
    User user = new User();
    BeanUtils.copyProperties(userQueryParam, user);
    // 筛选
    ExampleMatcher matcher =
        ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<User> example = Example.of(user, matcher);
    // 分页
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<User> userPage = userRepository.findAll(example, pageable);
    return CommonCodeEnum.SUCCESS.addData(
        "list",
        userPage.getContent(),
        "total",
        userPage.getTotalElements(),
        "totalPage",
        userPage.getTotalPages());
  }

  @Override
  public ResponseResult listAllUsers() {
    List<User> list = userRepository.findAll();
    return CommonCodeEnum.SUCCESS.addData("list", list, "total", list.size());
  }

  @Override
  public ResponseResult login(String username, String password) {
    User user = new User();
    user.setUsername(username);
    Example<User> example = Example.of(user);
    Optional<User> optional = userRepository.findOne(example);
    user =
        optional
            .map(u -> u.getPassword().equals(password) ? u : null)
            .orElseThrow(() -> new CustomException(CommonCodeEnum.USERNAME_OR_PASSWORD_ERROR));
    SessionUtils.setAttribute("user", user);
    user.setPassword(null);
    return CommonCodeEnum.SUCCESS.addData("user", user);
  }

  @Override
  public ResponseResult logout() {
    SessionUtils.invalidate();
    return CommonCodeEnum.SUCCESS.clearData();
  }
}
