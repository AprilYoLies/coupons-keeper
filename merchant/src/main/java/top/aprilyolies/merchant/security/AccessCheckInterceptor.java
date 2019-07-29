package top.aprilyolies.merchant.security;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static top.aprilyolies.merchant.constants.MerchantConstants.MERCHANT_TOKEN;
import static top.aprilyolies.merchant.constants.MerchantConstants.TOKEN_STR;

/**
 * 商户请求 token 检查拦截器
 */
@Component
public class AccessCheckInterceptor extends HandlerInterceptorAdapter {
    // 开始请求处理前，将商户的 token 信息保存到线程本地变量中
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN_STR);
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("商户请求的 header 中缺少必要的 token 信息！");
        }
        if (!token.equals(MERCHANT_TOKEN)) {
            throw new RuntimeException("商户请求的 token 信息有误！");
        }
        AccessContext.saveToken(token);
        return true;
    }

    // 请求完成后清理正在处理的商户 token 信息
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccessContext.clearToken();
    }
}
