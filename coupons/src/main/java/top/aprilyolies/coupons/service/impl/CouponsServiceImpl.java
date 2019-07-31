package top.aprilyolies.coupons.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.aprilyolies.coupons.constants.StatusCode;
import top.aprilyolies.coupons.mapper.CouponsMapper;
import top.aprilyolies.coupons.pojo.Coupon;
import top.aprilyolies.coupons.pojo.Response;
import top.aprilyolies.coupons.service.ICouponsService;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Slf4j
@Service
public class CouponsServiceImpl implements ICouponsService {
    private CouponsMapper couponsMapper;

    public CouponsServiceImpl(CouponsMapper couponsMapper) {
        this.couponsMapper = couponsMapper;
    }

    /**
     * 将 coupon 保存到数据库（不对重复优惠券进行验证）
     *
     * @param coupon 优惠券实例
     * @return 保存结果
     */
    @Override
    public Response saveCoupon(Coupon coupon) {
        int n = couponsMapper.saveCoupon(coupon);
        Response response;
        if (n > 0) {
            response = Response.buildResponse(StatusCode.SUCCESS);
        } else {
            response = Response.buildResponse(StatusCode.SAVE_COUPON_FAILED);
        }
        response.setData(coupon);
        return response;
    }
}
