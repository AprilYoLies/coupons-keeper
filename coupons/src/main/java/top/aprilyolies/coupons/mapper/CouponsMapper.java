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
}
