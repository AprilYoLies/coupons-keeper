package top.aprilyolies.coupons.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.aprilyolies.coupons.constants.CouponsConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static top.aprilyolies.coupons.constants.CouponsConstants.*;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Slf4j
@Controller
@RequestMapping("/merchant")
public class MerchantController {
    private StringRedisTemplate redisTemplate;

    public MerchantController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 上传 token 的界面
     *
     * @return token 上传视图名
     */
    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    /**
     * token 上传的请求处理
     *
     * @param merchantId         商家 id
     * @param couponId           优惠券 id
     * @param file               token 信息文件
     * @param redirectAttributes 重定向属性
     * @param model              数据模型
     */
    @RequestMapping("/token")
    public String token(@RequestParam("merchantId") String merchantId,
                        @RequestParam("couponId") String couponId,
                        @RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        if (null == couponId || file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message",
                    "passTemplateId is null or file is empty");
            return "redirect:/uploadStatus";
        }

        try {
            File cur = new File(CouponsConstants.TOKEN_DIR + MERCHANT_PREFIX + merchantId);
            if (!cur.exists()) {
                log.info("Create File: {}", cur.mkdir());
            }
            Path path = Paths.get(CouponsConstants.TOKEN_DIR, MERCHANT_PREFIX + merchantId, COUPON_PREFIX + couponId);
            Files.write(path, file.getBytes());

            if (!writeTokenToRedis(path, TOKEN_PREFIX + couponId)) {
                redirectAttributes.addFlashAttribute("message",
                        "write token error");
//                model.addAttribute("message", "write token error");
            } else {
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");
//                model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "redirect:/merchant/uploadStatus";
    }

    /**
     * 将 token 信息写入 redis
     *
     * @param path token 文件的位置
     * @param key  写入 redis 的 token 集合 key 信息
     * @return 写入结果
     */
    private boolean writeTokenToRedis(Path path, String key) {

        Set<String> tokens;

        try (Stream<String> stream = Files.lines(path)) {
            tokens = stream.collect(Collectors.toSet());
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

        if (!CollectionUtils.isEmpty(tokens)) {
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                for (String token : tokens) {
                    connection.sAdd(key.getBytes(), token.getBytes());
                }
                return null;
            });
            return true;
        }
        return false;
    }

    /**
     * 跳转 token 上传结果页面
     *
     * @return token 上传结果视图名
     */
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
