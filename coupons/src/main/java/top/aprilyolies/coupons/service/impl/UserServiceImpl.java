package top.aprilyolies.coupons.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.aprilyolies.coupons.constants.StatusCode;
import top.aprilyolies.coupons.mapper.UserMapper;
import top.aprilyolies.coupons.pojo.Response;
import top.aprilyolies.coupons.pojo.User;
import top.aprilyolies.coupons.service.IUserService;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 保存 user 信息到数据库中
     *
     * @param user user 实例
     * @return 创建 user 的结果
     */
    @Override
    public Response createUser(User user) {
        Response response;
        int n = userMapper.createUser(user);
        if (n > 0) {
            response = Response.buildResponse(StatusCode.SUCCESS);
        } else {
            response = Response.buildResponse(StatusCode.CREATE_USER_FAILED);
        }
        response.setData(user);
        return response;
    }
}
