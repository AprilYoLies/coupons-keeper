package top.aprilyolies.coupons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aprilyolies.coupons.constants.StatusCode;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */

/**
 * StatusCode 包裹类
 *
 * @param <T> 附加的数据内容
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusCodeWrapper<T> {
    // 状态码
    private StatusCode code;
    // 包裹类的数据
    private T data;
}
