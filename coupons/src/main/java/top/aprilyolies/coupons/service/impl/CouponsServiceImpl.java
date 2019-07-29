package top.aprilyolies.coupons.service.impl;

import org.springframework.stereotype.Service;
import top.aprilyolies.coupons.mapper.CouponsMapper;
import top.aprilyolies.coupons.pojo.Coupon;
import top.aprilyolies.coupons.service.ICouponsService;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Service
public class CouponsServiceImpl implements ICouponsService {
    private CouponsMapper couponsMapper;

    public CouponsServiceImpl(CouponsMapper couponsMapper) {
        this.couponsMapper = couponsMapper;
    }

    /**
     * 将 coupon 保存到数据库
     *
     * @param coupon
     */
    @Override
    public int saveCoupon(Coupon coupon) {
        return couponsMapper.saveCoupon(coupon);
    }
}
