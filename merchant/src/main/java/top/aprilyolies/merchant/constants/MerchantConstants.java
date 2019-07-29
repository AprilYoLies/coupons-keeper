package top.aprilyolies.merchant.constants;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
public class MerchantConstants {
    // http 请求头中的 token 字段
    public static final String TOKEN_STR = "token";
    // http 请求头中的 token 字段对应的值
    public static final String MERCHANT_TOKEN = "coupons_keeper_merchant_token";
    // kafka 消息中间件中，优惠券投放的主题
    public static final String COUPONS_TOPIC = "coupons_topic";
}
