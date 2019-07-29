package top.aprilyolies.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import top.aprilyolies.merchant.constants.StatusCode;
import top.aprilyolies.merchant.mapper.MerchantMapper;
import top.aprilyolies.merchant.pojo.Coupon;
import top.aprilyolies.merchant.pojo.IdHolder;
import top.aprilyolies.merchant.pojo.MerchantInfo;
import top.aprilyolies.merchant.pojo.Response;
import top.aprilyolies.merchant.service.IMerchantService;

import static top.aprilyolies.merchant.constants.MerchantConstants.COUPON_TOPIC;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Service
public class MerchantServiceImpl implements IMerchantService {
    private MerchantMapper merchantMapper;

    private KafkaTemplate<String, String> kafkaTemplate;

    public MerchantServiceImpl(MerchantMapper merchantMapper, KafkaTemplate kafkaTemplate) {
        this.merchantMapper = merchantMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 验证商户信息，通过后将商户信息写入数据库
     *
     * @param merchantInfo 商户信息实例
     * @return 商户信息写入的结果
     */
    @Override
    public Response createMerchants(MerchantInfo merchantInfo) {
        Response response = new Response();
        StatusCode code = merchantInfo.validate(merchantMapper);
        response.setCode(code.getCode());
        response.setMsg(code.getMsg());
        if (code != StatusCode.SUCCESS) {
            response.setData(new IdHolder(-1));
        } else {
            merchantMapper.createMerchants(merchantInfo);
            response.setData(new IdHolder(merchantInfo.getId()));
        }
        return response;
    }

    /**
     * 根据商户 id 获取商户信息
     *
     * @param id 商户 id
     * @return 商户信息
     */
    @Override
    public Response queryMerchantsInfo(Integer id) {
        Response response;
        MerchantInfo merchantInfo = merchantMapper.findById(id);
        if (merchantInfo == null) {
            response = Response.buildResponse(StatusCode.MERCHANTS_NOT_EXIST);
        } else {
            response = Response.buildResponse(StatusCode.SUCCESS);
        }
        response.setData(merchantInfo);
        return response;
    }

    /**
     * 商户投放优惠券
     *
     * @param coupon 优惠券信息
     * @return 投放结果
     */
    @Override
    public Response dropCoupon(Coupon coupon) {
        StatusCode code = coupon.validate(merchantMapper);
        Response response = Response.buildResponse(code);
        if (code == StatusCode.SUCCESS) {
            String couponStr = JSON.toJSONString(coupon);
            kafkaTemplate.send(COUPON_TOPIC, couponStr);
        }
        return response;
    }
}
