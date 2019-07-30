package top.aprilyolies.coupons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCoupon {
    // 用户优惠券 id
    private int id;
    // 用户 id
    private int userId;
    // 优惠券 id
    private int couponId;
    // 优惠券 token
    private String token = "";
    // 优惠券领取日期
    private Date assignDate;
    // 优惠券使用日期
    private Date consumeDate;
}
