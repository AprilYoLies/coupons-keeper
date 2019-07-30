package top.aprilyolies.coupons.service;

import top.aprilyolies.coupons.pojo.Response;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */

/**
 * 用户优惠券服务接口
 */
public interface IUserCouponsService {
    /**
     * 用户领取优惠券
     *
     * @param userId   用户 id
     * @param couponId 领取的优惠券 id
     * @return 领取优惠券的结果信息
     */
    Response gainCouponForUser(int userId, int couponId);

    /**
     * 根据用户 id 获取该用户领取的全部优惠券
     *
     * @param userId 用户 id
     * @return 查询的用户优惠券结果
     */
    Response queryUserCoupons(int userId);

    /**
     * 根据用户 id 获取该用户已使用的优惠券
     *
     * @param userId 用户 id
     * @return 查询的用户优惠券结果
     */
    Response queryUsedCoupons(int userId);
}
