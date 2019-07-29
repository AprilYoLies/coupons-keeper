package top.aprilyolies.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.aprilyolies.merchant.pojo.MerchantInfo;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

/**
 * 商户映射接口
 */
@Mapper
public interface MerchantMapper {
    /**
     * 将商户信息保存到数据库
     *
     * @param merchantInfo 商户信息
     * @return 创建的商户的 id
     */
    int createMerchants(MerchantInfo merchantInfo);

    /**
     * 根据商户名查找商户细信息
     *
     * @param name 商户名
     * @return 商户信息
     */
    MerchantInfo findByName(String name);

    /**
     * 根据商户 id 查找商户细信息
     *
     * @param id 商户名
     * @return 商户信息
     */
    MerchantInfo findById(Integer id);
}
