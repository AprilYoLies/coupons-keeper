package top.aprilyolies.coupons.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.aprilyolies.coupons.pojo.Response;
import top.aprilyolies.coupons.service.IUserCouponsService;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/coupons")
public class UserCouponsController {
    private IUserCouponsService userCouponsService;

    public UserCouponsController(IUserCouponsService userCouponsService) {
        this.userCouponsService = userCouponsService;
    }

    /**
     * 用户领取优惠券
     *
     * @param userId   用户 id
     * @param couponId 优惠券 id
     * @return 领取优惠券的响应结果
     */
    @RequestMapping("/gain-coupon/{userId}/{couponId}")
    public Response gainCouponForUser(@PathVariable int userId, @PathVariable int couponId) {
        log.info("GainCouponForUser: userId {} couponId {}", userId, couponId);
        return userCouponsService.gainCouponForUser(userId, couponId);
    }
}
