package top.aprilyolies.coupons.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.aprilyolies.coupons.pojo.Coupon;

import java.util.List;
import java.util.Set;

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

    /**
     * 排除方式的查找，查询结果不包括参数集合中的内容
     *
     * @param excludeIds 被排除的项
     * @return 符合条件的优惠券信息
     */
    List<Coupon> excludedFind(@Param("excludeIds") Set<Integer> excludeIds);

    /**
     * 获取全部的优惠券信息
     *
     * @return 全部的优惠券信息
     */
    List<Coupon> findAll();
}
