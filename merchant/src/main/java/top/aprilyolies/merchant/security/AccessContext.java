package top.aprilyolies.merchant.security;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

/**
 * 请求访问上下文环境，用于保存各种类型的线程本地变量
 */
public class AccessContext {
    // 线程本地变量，用于保存当前正在处理的商户 token 信息
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    /**
     * 将商户 token 信息保存到线程本地变量中
     *
     * @param token 商户 token 信息
     */
    public static void saveToken(String token) {
        AccessContext.token.set(token);
    }

    /**
     * 从线程本地变量中，拿到当前正在处理的商户 token 信息
     *
     * @return
     */
    public static String getToken() {
        return token.get();
    }

    /**
     * 清理线程本地变量中正在处理的的商户 token 信息
     */
    public static void clearToken() {
        token.remove();
    }
}
