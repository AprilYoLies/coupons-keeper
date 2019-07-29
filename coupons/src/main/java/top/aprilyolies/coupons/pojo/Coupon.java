package top.aprilyolies.coupons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    // 商户 id
    private Integer merchantId;
    // 优惠券标题
    private String title;
    // 摘要信息
    private String summary;
    // 详细信息
    private String desc;
    // 领取上限
    private Long limit;
    // 是否有 token
    private Boolean hasToken;
    // 背景色
    private Integer background;
    // 有效起始日期
    private Date start;
    // 有效截止日期
    private Date end;
}
