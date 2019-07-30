package top.aprilyolies.coupons.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.aprilyolies.coupons.pojo.Coupon;
import top.aprilyolies.coupons.pojo.Response;

import java.util.Date;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponsServiceTest {
    @Autowired
    private ICouponsService couponsService;

    @Test
    public void testSaveCoupon() {
        Coupon coupon = new Coupon();
        coupon.setBackground(1);
        coupon.setDesc("华莱士8.8折优惠券");
        coupon.setEnd(DateUtils.addDays(new Date(), 365));
        coupon.setHasToken(true);
        coupon.setLimit(10000L);
        coupon.setMerchantId(1);
        coupon.setStart(new Date());
        coupon.setTitle("华莱士优惠券");
        coupon.setSummary("原味手撕鸡");
        Response response = couponsService.saveCoupon(coupon);
        System.out.println(JSON.toJSONString(response));
    }
}
