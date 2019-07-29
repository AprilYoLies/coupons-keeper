package top.aprilyolies.coupons.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import top.aprilyolies.coupons.constants.CouponsConstants;
import top.aprilyolies.coupons.pojo.Coupon;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

/**
 * kafka 消息中间件中，消息的消费者
 */
@Slf4j
@Component
public class CouponsConsumer {
    private ICouponsService couponsService;

    public CouponsConsumer(ICouponsService couponsService) {
        this.couponsService = couponsService;
    }

    /**
     * 消费消息中间件中的消息，将其存储到数据库中
     *
     * @param couponStr 优惠券消息串
     */
    @KafkaListener(topics = {CouponsConstants.COUPONS_TOPIC})
    public void consumeCoupons(@Payload String couponStr) {
        log.info("Consumer Receive Coupon: {}", couponStr);
        Coupon coupon;
        try {
            coupon = JSON.parseObject(couponStr, Coupon.class);
        } catch (Exception e) {
            log.error("Parse Coupon Error: {}", e.getMessage());
            return;
        }
        int n = couponsService.saveCoupon(coupon);
        log.info("Save coupon {} {}", couponStr, n > 0 ? "success" : "failed");
    }
}
