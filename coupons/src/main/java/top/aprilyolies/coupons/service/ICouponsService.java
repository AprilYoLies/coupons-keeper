package top.aprilyolies.coupons.service;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

import top.aprilyolies.coupons.pojo.Coupon;
import top.aprilyolies.coupons.pojo.Response;

/**
 * 优惠券相关的服务
 */
public interface ICouponsService {
    /**
     * 将 coupon 保存到数据库
     *
     * @param coupon
     */
    Response saveCoupon(Coupon coupon);
}
