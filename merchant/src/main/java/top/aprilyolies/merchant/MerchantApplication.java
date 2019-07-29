package top.aprilyolies.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.aprilyolies.merchant.security.AccessCheckInterceptor;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@SpringBootApplication
public class MerchantApplication implements WebMvcConfigurer {
    private AccessCheckInterceptor interceptor;

    public MerchantApplication(AccessCheckInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(MerchantApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (interceptor != null) {
            registry.addInterceptor(interceptor);
        }
    }
}
