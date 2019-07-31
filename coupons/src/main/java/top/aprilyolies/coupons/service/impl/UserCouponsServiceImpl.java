package top.aprilyolies.coupons.service.impl;

import org.springframework.stereotype.Service;
import top.aprilyolies.coupons.constants.StatusCode;
import top.aprilyolies.coupons.mapper.CouponsMapper;
import top.aprilyolies.coupons.mapper.UserCouponsMapper;
import top.aprilyolies.coupons.mapper.UserMapper;
import top.aprilyolies.coupons.pojo.*;
import top.aprilyolies.coupons.service.IUserCouponsService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */
@Service
public class UserCouponsServiceImpl implements IUserCouponsService {
    private UserMapper userMapper;

    private CouponsMapper couponsMapper;

    private UserCouponsMapper userCouponsMapper;

    public UserCouponsServiceImpl(UserMapper userMapper, CouponsMapper couponsMapper, UserCouponsMapper userCouponsMapper) {
        this.userMapper = userMapper;
        this.couponsMapper = couponsMapper;
        this.userCouponsMapper = userCouponsMapper;
    }

    /**
     * 用户领取优惠券
     *
     * @param userId   用户 id
     * @param couponId 领取的优惠券 id
     * @return 领取优惠券的结果信息
     */
    @Override
    public Response gainCouponForUser(int userId, int couponId) {
        StatusCode code = checkUserExisted(userId);
        if (code != StatusCode.SUCCESS) {
            return Response.buildResponse(code).setData(-1);
        }
        StatusCodeWrapper<Coupon> wrapper = checkCouponExisted(couponId);
        code = wrapper.getCode();
        if (code != StatusCode.SUCCESS) {
            return Response.buildResponse(code).setData(-1);
        }
        Coupon coupon = wrapper.getData();
        if (!updateCouponStock(coupon)) {
            return Response.buildResponse(StatusCode.UPDATE_COUPON_STOCK_FAILED).setData(-1);
        }
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setAssignDate(new Date());
        userCoupon.setCouponId(couponId);
        if (coupon.getHasToken()) {
            // TODO 获取优惠券 Token，设置 Token 信息
        }
        userCoupon.setUserId(userId);
        int c = userCouponsMapper.saveUserCoupon(userCoupon);
        if (c < 1) {
            return Response.buildResponse(StatusCode.SAVE_USER_COUPONS_FAILED).setData(-1);
        }
        return Response.buildResponse(StatusCode.SUCCESS).setData(userCoupon);
    }

    /**
     * 根据用户 id 获取该用户领取的全部优惠券
     *
     * @param userId 用户 id
     * @return 查询的用户优惠券结果
     */
    @Override
    public Response queryUserCoupons(int userId) {
        List<UserCoupon> userCoupons = userCouponsMapper.findAllByUserId(userId);
        if (userCoupons == null || userCoupons.size() == 0) {
            return Response.buildResponse(StatusCode.EMPTY_USER_COUPONS).setData(-1);
        }
        return Response.buildResponse(StatusCode.SUCCESS).setData(userCoupons);
    }

    /**
     * 根据用户 id 获取该用户已使用的优惠券
     *
     * @param userId 用户 id
     * @return 查询的用户优惠券结果
     */
    @Override
    public Response queryUsedCoupons(int userId) {
        List<UserCoupon> usedCoupons = userCouponsMapper.findUsedByUserId(userId);
        if (usedCoupons == null || usedCoupons.size() == 0) {
            return Response.buildResponse(StatusCode.EMPTY_USED_COUPONS).setData(-1);
        }
        return Response.buildResponse(StatusCode.SUCCESS).setData(usedCoupons);
    }

    /**
     * 使用用户指定的优惠券
     *
     * @param userCouponId 用户优惠券 id
     * @return 使用的结果
     */
    @Override
    public Response useCoupon(int userCouponId) {
        UserCoupon userCoupon = userCouponsMapper.findById(userCouponId);
        if (userCoupon == null) {
            return Response.buildResponse(StatusCode.COUPON_NOT_EXIST).setData(userCouponId);
        }
        if (userCoupon.getConsumeDate() != null) {
            return Response.buildResponse(StatusCode.COUPON_HAS_USED).setData(userCouponId);
        }
        userCoupon.setConsumeDate(new Date());
        int n = userCouponsMapper.updateUserCoupon(userCoupon);
        if (n < 1) {
            return Response.buildResponse(StatusCode.UPDATE_USER_COUPON_FAILED).setData(userCouponId);
        }
        return Response.buildResponse(StatusCode.SUCCESS).setData(userCoupon);
    }

    /**
     * 获取用户可领取的优惠券，不包括已领取优惠券和失效
     *
     * @param userId 用户 id
     * @return 可获取的优惠券信息
     */
    @Override
    public Response availableCoupons(int userId) {
        StatusCode code = checkUserExisted(userId);
        if (code != StatusCode.SUCCESS) {
            return Response.buildResponse(code).setData(-1);
        }
        @SuppressWarnings("unchecked") List<UserCoupon> coupons = (List<UserCoupon>) queryUserCoupons(userId).getData();
        Set<Integer> excludeIds = new HashSet<>(coupons.size());
        for (UserCoupon coupon : coupons) {
            excludeIds.add(coupon.getCouponId());
        }
        List<Coupon> availableCoupons = couponsMapper.excludedFind(excludeIds);
        if (availableCoupons == null || availableCoupons.size() == 0) {
            return Response.buildResponse(StatusCode.EMPTY_AVAILABLE_COUPONS).setData(userId);
        }
        return Response.buildResponse(StatusCode.SUCCESS).setData(availableCoupons);
    }

    /**
     * 更新优惠券的库存信息
     *
     * @param coupon 优惠券
     * @return 更新结果
     */
    private boolean updateCouponStock(Coupon coupon) {
        if (coupon.getLimit() < 0) {
            return true;
        }
        if (coupon.getLimit() == 0) {
            return false;
        }
        coupon.setLimit(coupon.getLimit() - 1);
        int n = couponsMapper.updateCoupon(coupon);
        return n >= 1;
    }

    /**
     * 检查参数 id 对应的优惠券是否存在
     *
     * @param couponId 用户 id
     * @return 状态码
     */
    private StatusCodeWrapper<Coupon> checkCouponExisted(int couponId) {
        Coupon coupon = couponsMapper.findById(couponId);
        StatusCodeWrapper<Coupon> wrapper = new StatusCodeWrapper<>();
        if (coupon == null) {
            wrapper.setCode(StatusCode.COUPON_NOT_EXIST);
            return wrapper;
        }
        if (coupon.getLimit() == 0L) {
            wrapper.setCode(StatusCode.COUPON_STOCK_EMPTY);
            return wrapper;
        }
        wrapper.setCode(StatusCode.SUCCESS);
        wrapper.setData(coupon);
        return wrapper;
    }

    /**
     * 检查参数 id 对应的用户是否存在
     *
     * @param userId 用户 id
     * @return 状态码
     */
    private StatusCode checkUserExisted(int userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            return StatusCode.USER_NOT_EXIST;
        }
        return StatusCode.SUCCESS;
    }
}
