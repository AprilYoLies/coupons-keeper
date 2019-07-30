package top.aprilyolies.coupons.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.aprilyolies.coupons.pojo.Response;
import top.aprilyolies.coupons.pojo.User;
import top.aprilyolies.coupons.service.IUserService;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/coupons")
public class UserController {
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public Response createUser(@RequestBody User user) {
        log.info("CreateUser: {}", JSON.toJSONString(user));
        return userService.createUser(user);
    }
}
