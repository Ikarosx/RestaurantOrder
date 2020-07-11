package cn.ikarosx.restaurant.filter;

import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.util.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ikarosx
 * @date 2020/7/9 12:09
 */
@Component
@Order(100)
public class AuthorizationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    if (requestURI.equals("/user/login")
        || (requestURI.equals("/user")
            && request.getMethod().equalsIgnoreCase(HttpMethod.POST.name()))
        || StringUtils.startsWithAny(requestURI, "/swagger", "/favicon.ico", "/webjars", "/v2")) {
      doFilter(request, response, filterChain);
      return;
    }
    Object user = SessionUtils.getAttribute("user");
    if (user != null) {
      doFilter(request, response, filterChain);
      return;
    }
    response.setStatus(200);
    response.setContentType("application/json;charset=utf-8");
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(response.getWriter(), CommonCodeEnum.RE_LOGIN);
  }
}
