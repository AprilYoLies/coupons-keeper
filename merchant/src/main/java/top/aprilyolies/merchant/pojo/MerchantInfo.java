package top.aprilyolies.merchant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aprilyolies.merchant.constants.StatusCode;
import top.aprilyolies.merchant.mapper.MerchantMapper;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantInfo {
    // 商户 id
    private int id;
    // 商户名
    private String name;
    //  商户 logo url
    private String logoUrl;
    // 商户证书 url
    private String businessLicenseUrl;
    // 商户联系电话
    private String phone;
    // 商户地址
    private String address;
    // 是否通过审核
    private int idAudit = 0;

    /**
     * 验证商户信息
     *
     * @param merchantMapper 商户 mapper 接口
     * @return 验证的结果
     */
    public StatusCode validate(MerchantMapper merchantMapper) {
        MerchantInfo info = merchantMapper.findByName(name);
        if (info != null) {
            return StatusCode.DUPLICATE_NAME;
        }
        if (null == this.logoUrl) {
            return StatusCode.EMPTY_LOGO;
        }

        if (null == this.businessLicenseUrl) {
            return StatusCode.EMPTY_BUSINESS_LICENSE;
        }

        if (null == this.address) {
            return StatusCode.EMPTY_ADDRESS;
        }

        if (null == this.phone) {
            return StatusCode.ERROR_PHONE;
        }

        return StatusCode.SUCCESS;
    }
}
