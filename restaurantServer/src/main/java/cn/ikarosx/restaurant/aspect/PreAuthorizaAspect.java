package cn.ikarosx.restaurant.aspect;

import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.exception.ExceptionCast;
import cn.ikarosx.restaurant.util.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * SpEL鉴权
 *
 * @author Ikarosx
 * @date 2020/7/25 9:38
 */
@Aspect
@Component
@Slf4j
public class PreAuthorizaAspect implements BeanFactoryAware {
  private ExpressionParser parser = new SpelExpressionParser();
  private LocalVariableTableParameterNameDiscoverer discoverer =
      new LocalVariableTableParameterNameDiscoverer();

  private BeanFactory beanFactory;
  private BeanFactoryResolver beanResolver;

  @Pointcut("@annotation(PreAuthorize)")
  public void preAuthorizaPointCut() {}

  @Before("preAuthorizaPointCut()")
  public void preAuthorizaBefore(JoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();
    PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
    String spel = preAuthorize.value();

    String[] params = discoverer.getParameterNames(method);
    StandardEvaluationContext context = new StandardEvaluationContext();
    Object[] args = joinPoint.getArgs();
    for (int len = 0; len < params.length; len++) {
      context.setVariable(params[len], args[len]);
    }
    context.setBeanResolver(beanResolver);
    Expression expression = parser.parseExpression(spel);
    if (!expression.getValue(context, Boolean.class)) {
      log.warn(SessionUtils.getUserName() + "在越权访问" + method.getName());
      ExceptionCast.cast(CommonCodeEnum.PERMISSION_DENY);
    }
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
    this.beanResolver = new BeanFactoryResolver(beanFactory);
  }
}
