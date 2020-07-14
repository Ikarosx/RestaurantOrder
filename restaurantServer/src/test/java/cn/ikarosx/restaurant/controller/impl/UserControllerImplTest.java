package cn.ikarosx.restaurant.controller.impl;

import cn.ikarosx.restaurant.RestaurantApplication;
import cn.ikarosx.restaurant.entity.User;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.Rollback;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ikarosx
 * @date 2020/7/13 22:58
 */
@SpringBootTest(
    classes = RestaurantApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerImplTest {
  @Autowired private TestRestTemplate restTemplate;
  private String userId = "8a80cb81734ce3fa01734ce40d230000";

  @Test
  @Order(1)
  @Rollback
  void insertUser() {
    User user = new User();
    user.setPassword("test");
    user.setUsername("Test");
    String username = "Test";
    String password = "test";
    Map Map = restTemplate.postForObject("/user", user, Map.class);
    System.out.println(JSON.toJSONString(Map));
    assert (boolean) Map.get("success");
  }

  @Test
  @Order(2)
  void login() {
    User user = new User();
    user.setPassword("test");
    user.setUsername("Test");
    String username = "Test";
    String password = "test";
    HashMap<String, Object> param = new HashMap<>();
    param.put("username", username);
    param.put("password", password);
    Map map =
        restTemplate.postForObject(
            "/user/login?username=" + username + "&password=" + password,
            HttpMethod.POST,
            Map.class,
            param);
    System.out.println(JSON.toJSONString(map));
    assert (boolean) map.get("success");
  }

  @Test
  @Order(3)
  void getUserById() {

    Map Map = restTemplate.getForObject("/user/" + userId, Map.class);
    System.out.println(JSON.toJSONString(Map));
    assert (boolean) Map.get("success");
  }

  @Test
  @Order(4)
  void updateUser() {
    User user = new User();
    user.setId(userId);
    user.setUsername("Ikarosxxxx");
    restTemplate.put("/user/" + userId, user);
  }

  @Test
  @Order(5)
  void getUserById2() {
    Map Map = restTemplate.getForObject("/user/" + userId, Map.class);
    System.out.println(JSON.toJSONString(Map));
    assert (boolean) Map.get("success");
  }

  @Test
  @Order(6)
  void deleteUserById() {
    restTemplate.delete("/user/" + userId);
  }

  @Test
  @Order(7)
  void listUsersByPage() {
    Map Map = restTemplate.getForObject("/user/0/0", Map.class);
    System.out.println(JSON.toJSONString(Map));
    assert (boolean) Map.get("success");
  }

  @Test
  @Order(8)
  void logout() {
    Map Map = restTemplate.getForObject("/user/logout", Map.class);
    System.out.println(JSON.toJSONString(Map));
    assert (boolean) Map.get("success");
  }

  @Test
  @Order(9)
  void listAllUsers() {
    Map Map = restTemplate.getForObject("/user/", Map.class);
    System.out.println(JSON.toJSONString(Map));
    assert (boolean) Map.get("success");
  }
}
