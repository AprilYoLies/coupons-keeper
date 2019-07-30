package top.aprilyolies.coupons.pojo;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aprilyolies.coupons.constants.StatusCode;

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

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据状态码构建 Response 实例
     *
     * @param code 状态码
     * @return Response 实例
     */
    public static Response buildResponse(StatusCode code) {
        return new Response(code.getCode(), code.getMsg());
    }
}
