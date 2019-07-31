package top.aprilyolies.coupons.constants;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
public class CouponsConstants {
    // kafka 消息中间件中，优惠券投放的主题
    public static final String COUPONS_TOPIC = "coupons_topic";
    // token 信息存放路径
    public static final String TOKEN_DIR = "/tmp/token/";
    // 存储到 redis 中的 token 集合前缀
    public static final String TOKEN_PREFIX = "token-";
    // 商家前缀
    public static final String MERCHANT_PREFIX = "merchant-";
    // 优惠券前缀
    public static final String COUPON_PREFIX = "coupon-";
}
