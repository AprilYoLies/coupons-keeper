package top.aprilyolies.merchant.service.impl;

import org.springframework.stereotype.Service;
import top.aprilyolies.merchant.constants.StatusCode;
import top.aprilyolies.merchant.mapper.MerchantMapper;
import top.aprilyolies.merchant.pojo.MerchantIdHolder;
import top.aprilyolies.merchant.pojo.MerchantInfo;
import top.aprilyolies.merchant.pojo.Response;
import top.aprilyolies.merchant.service.IMerchantService;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Service
public class MerchantServiceImpl implements IMerchantService {
    private MerchantMapper merchantMapper;

    public MerchantServiceImpl(MerchantMapper merchantMapper) {
        this.merchantMapper = merchantMapper;
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
            response.setData(new MerchantIdHolder(-1));
        } else {
            merchantMapper.createMerchants(merchantInfo);
            response.setData(new MerchantIdHolder(merchantInfo.getId()));
        }
        return response;
    }
}
