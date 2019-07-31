package top.aprilyolies.coupons.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.aprilyolies.coupons.constants.StatusCode;
import top.aprilyolies.coupons.pojo.Response;

/**
 * @Author EvaJohnson
 * @Date 2019-07-31
 * @Email g863821569@gmail.com
 */

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response errorHandler(Exception ex) {
        return Response.buildResponse(StatusCode.EXCEPTION).setData(ex.getMessage());
    }
}
