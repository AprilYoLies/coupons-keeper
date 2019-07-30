package top.aprilyolies.coupons.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.aprilyolies.coupons.pojo.User;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("华玲");
        user.setSex("女");
        user.setAddress("四川省成都市");
        user.setAge(23);
        user.setPhone("1234567890");
        System.out.println(JSON.toJSONString(userService.createUser(user)));
    }
}
