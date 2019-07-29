package top.aprilyolies.merchant.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.aprilyolies.merchant.pojo.MerchantInfo;
import top.aprilyolies.merchant.pojo.Response;
import top.aprilyolies.merchant.service.IMerchantService;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    private IMerchantService merchantService;

    public MerchantController(IMerchantService merchantService) {
        this.merchantService = merchantService;
    }

    /**
     * 创建商户信息
     *
     * @param merchantInfo 商户信息
     * @return 操作的结果
     */
    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody MerchantInfo merchantInfo) {
        log.info("CreateMerchants: {}", JSON.toJSONString(merchantInfo));
        return merchantService.createMerchants(merchantInfo);
    }

    /**
     * 获取商户信息
     *
     * @param id 商户信息
     * @return 操作结果，数据为商户信息
     */
    @ResponseBody
    @GetMapping("/{id}")
    public Response queryMerchantsInfo(@PathVariable Integer id) {
        log.info("BuildMerchantsInfo: {}", id);
        return merchantService.queryMerchantsInfo(id);
    }
}
