package top.aprilyolies.merchant.service;

import top.aprilyolies.merchant.pojo.Coupon;
import top.aprilyolies.merchant.pojo.MerchantInfo;
import top.aprilyolies.merchant.pojo.Response;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

/**
 * 商户服务接口
 */
public interface IMerchantService {
    // 创建商户信息
    Response createMerchants(MerchantInfo merchantInfo);

    // 根据 id 查询商户信息
    Response queryMerchantsInfo(Integer id);

    // 商户投放优惠券
    Response dropCoupon(Coupon coupon);
}
