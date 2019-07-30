package top.aprilyolies.coupons.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.aprilyolies.coupons.pojo.UserCoupon;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */

/**
 * 用户优惠券 mapper
 */
@Mapper
public interface UserCouponsMapper {
    /**
     * 存储用户优惠券信息
     *
     * @param userCoupon 用户优惠券实例
     * @return 影响的记录数
     */
    int saveUserCoupon(UserCoupon userCoupon);
}
