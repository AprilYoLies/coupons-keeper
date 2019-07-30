package top.aprilyolies.coupons.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.aprilyolies.coupons.pojo.User;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */

/**
 * 用户 mapper 接口
 */
@Mapper
public interface UserMapper {
    /**
     * 保存 user 信息到数据库中
     *
     * @param user user 实例
     * @return 创建 user 的结果
     */
    int createUser(User user);
}
