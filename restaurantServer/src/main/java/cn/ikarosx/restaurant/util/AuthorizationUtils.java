package cn.ikarosx.restaurant.util;

import cn.ikarosx.restaurant.entity.User;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Ikarosx
 * @date 2020/7/9 12:58
 */
public class AuthorizationUtils {
  public static boolean isOwner(String id) {
    User user = SessionUtils.getUser();
    return StringUtils.equals(user.getId(), id);
  }
}
