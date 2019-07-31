package top.aprilyolies.coupons.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.aprilyolies.coupons.pojo.UserCoupon;

import java.util.List;

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

    /**
     * 根据用户 id 获取该用户领取的全部优惠券
     *
     * @param userId 用户 id
     * @return 查询的用户优惠券结果
     */
    List<UserCoupon> findAllByUserId(int userId);

    /**
     * 根据用户 id 获取该用户使用过的全部优惠券
     *
     * @param userId 用户 id
     * @return 查询的用户使用过的优惠券结果
     */
    List<UserCoupon> findUsedByUserId(int userId);

    /**
     * 根据 userCouponId 查询用户优惠券
     *
     * @param userCouponId 用户优惠券 id
     * @return 查询的用户优惠券信息
     */
    UserCoupon findById(int userCouponId);

    /**
     * 更新用户优惠券信息
     *
     * @param userCoupon 更新的用户优惠券信息
     * @return 影响的记录数
     */
    int updateUserCoupon(UserCoupon userCoupon);

    /**
     * 根据 userId 和 couponId 获取优惠券信息
     *
     * @param userId   用户 id
     * @param couponId 优惠券 id
     * @return 优惠券结果
     */
    UserCoupon findByUserIDAndCouponId(int userId, int couponId);
}
