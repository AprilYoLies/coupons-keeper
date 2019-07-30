package top.aprilyolies.coupons.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.aprilyolies.coupons.pojo.Coupon;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

/**
 * 商户映射接口
 */
@Mapper
public interface CouponsMapper {
    /**
     * 将 coupon 保存到数据库
     *
     * @param coupon
     * @return 影响的记录数
     */
    int saveCoupon(Coupon coupon);

    /**
     * 根据参数 id 查找对应的优惠券
     *
     * @param couponId 优惠券 id
     * @return 优惠券实例
     */
    Coupon findById(int couponId);

    /**
     * 更新优惠券信息
     *
     * @param coupon 优惠券信息
     * @return 影响记录数
     */
    int updateCoupon(Coupon coupon);
}
