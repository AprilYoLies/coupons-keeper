package top.aprilyolies.coupons.service;

import top.aprilyolies.coupons.pojo.Response;
import top.aprilyolies.coupons.pojo.User;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
public interface IUserService {
    /**
     * 保存 user 信息到数据库中
     *
     * @param user user 实例
     * @return 创建 user 的结果
     */
    Response createUser(User user);
}
