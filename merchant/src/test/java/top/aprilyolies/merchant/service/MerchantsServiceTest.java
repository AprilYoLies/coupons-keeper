package top.aprilyolies.merchant.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.aprilyolies.merchant.pojo.Coupon;
import top.aprilyolies.merchant.pojo.MerchantInfo;

import java.util.Date;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServiceTest {
    @Autowired
    private IMerchantService merchantService;

    @Test
    @Transactional
    public void testCreateMerchantServ() {
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setName("知乎");
        merchantInfo.setLogoUrl("www.imooc.com");
        merchantInfo.setBusinessLicenseUrl("www.imooc.com");
        merchantInfo.setPhone("1234567890");
        merchantInfo.setAddress("北京市");
        System.out.println(JSON.toJSONString(merchantService.createMerchants(merchantInfo)));
    }

    @Test
    public void testBuildMerchantsInfoById() {
        System.out.println(JSON.toJSONString(merchantService.queryMerchantsInfo(1)));
    }

    @Test
    public void testDropPassTemplate() {
        Coupon coupon = new Coupon();
        coupon.setMerchantId(1);
        coupon.setTitle("肯德基优惠券-无token");
        coupon.setSummary("肯德基优惠券摘要");
        coupon.setDesc("肯德基优惠券详情");
        coupon.setLimit(10000L);
        coupon.setHasToken(false);
        coupon.setBackground(2);
        coupon.setStart(DateUtils.addDays(new Date(), -365));
        coupon.setEnd(DateUtils.addDays(new Date(), 365));
        System.out.println(JSON.toJSONString(
                merchantService.dropCoupon(coupon)
        ));
    }
}
