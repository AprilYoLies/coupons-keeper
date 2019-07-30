package top.aprilyolies.coupons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 用户 id
    private int id;
    // 用户名
    private String name;
    // 年龄
    private Integer age;
    // 性别
    private String sex;
    // 联系电话
    private String phone;
    // 住址
    private String address;
}
