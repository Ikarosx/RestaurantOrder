package cn.ikarosx.restaurant.exception;

/**
 * @author Ikaros
 * @date 2020/1/26 18:19
 */
public class CustomException extends RuntimeException {
  private ResponseResult responseResult;

  public CustomException() {}

  public CustomException(ResponseResult resultCode) {
    super("错误代码：" + resultCode.getCode() + "错误信息：" + resultCode.getMessage());
    this.responseResult = resultCode;
  }

  public ResponseResult getResponseResult() {
    return responseResult;
  }
}
