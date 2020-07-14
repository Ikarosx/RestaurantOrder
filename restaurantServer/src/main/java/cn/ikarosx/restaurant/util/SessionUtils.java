package cn.ikarosx.restaurant.util;

import cn.ikarosx.restaurant.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Ikarosx
 * @date 2020/7/9 0:48
 */
public class SessionUtils {
  public static HttpSession getSession() {
    ServletRequestAttributes requestAttributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    assert requestAttributes != null;
    return requestAttributes.getRequest().getSession();
  }

  public static void setAttribute(String key, Object value) {
    getSession().setAttribute(key, value);
  }

  public static Object getAttribute(String key) {
    return getSession().getAttribute(key);
  }

  public static void removeAttribute(String key) {
    getSession().removeAttribute(key);
  }

  public static void invalidate() {
    getSession().invalidate();
  }

  public static User getUser() {
    return (User) getAttribute("user");
  }

  public static String getId() {
    return getUser().getId();
  }

  public static String getUserName() {
    return getUser().getUsername();
  }
}
