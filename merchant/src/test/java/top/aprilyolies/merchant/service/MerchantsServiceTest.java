package top.aprilyolies.merchant.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.aprilyolies.merchant.pojo.MerchantInfo;

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

}
