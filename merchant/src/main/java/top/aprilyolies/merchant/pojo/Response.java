package top.aprilyolies.merchant.pojo;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aprilyolies.merchant.constants.StatusCode;

/**
 * 响应实例
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    // 响应代码
    private Integer code = 0;
    // 响应信息
    private String msg = "";
    // 响应数据
    private Object data;

    /**
     * 根据 StatusCode 构建 Response
     *
     * @param statusCode 状态码
     * @return 响应实例
     */
    public static Response buildResponse(StatusCode statusCode) {
        Response response = new Response();
        response.setCode(statusCode.getCode());
        response.setMsg(statusCode.getMsg());
        return response;
    }
}
