package cn.ikarosx.restaurant.exception;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ikaros
 * @date 2020/1/26 18:24
 */
@RestControllerAdvice
@Slf4j
public class ExceptionCatch {

  /** 使用EXCEPTIONS存放异常类型和错误代码的映射， ImmutableMap的特点的一旦创建不可改变，并且线程安全 */
  private static ImmutableMap<Class<? extends Throwable>, ResponseResult> EXCEPTIONS;

  /** 使用builder来构建一个异常类型和错误代码的异常 */
  private static ImmutableMap.Builder<Class<? extends Throwable>, ResponseResult> builder =
      ImmutableMap.builder();

  static {
    // 在这里加入一些基础的异常类型判断
    builder.put(HttpMessageNotReadableException.class, CommonCodeEnum.INVALID_PARAM);
    builder.put(
        DataIntegrityViolationException.class, CommonCodeEnum.DATA_INTEGRITY_VIOLATION_EXCEPTION);
  }

  @ExceptionHandler(CustomException.class)
  public ResponseResult customException(CustomException e) {
    log.error("catch exception : {}", e.getMessage());
    return e.getResponseResult();
  }
  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseResult customException(EmptyResultDataAccessException e) {
    log.error("catch exception : {}", e.getMessage());
    // DAO访问数据为空
    // TODO
    return CommonCodeEnum.SUCCESS;
  }

  @ExceptionHandler(Exception.class)
  public ResponseResult exception(Exception exception) {
    // 记录日志
    log.error("catch exception:{}", exception.getClass() + "------" + exception.getMessage());
    if (EXCEPTIONS == null) {
      EXCEPTIONS = builder.build();
    }
    final ResponseResult resultCode = EXCEPTIONS.get(exception.getClass());
    final ResponseResult responseResult;
    if (resultCode != null) {
      responseResult = resultCode;
    } else {
      responseResult = CommonCodeEnum.SERVER_ERROR;
    }
    return responseResult;
  }
}
