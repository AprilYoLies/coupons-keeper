package top.aprilyolies.coupons.constants;

/**
 * @Author EvaJohnson
 * @Date 2019-07-30
 * @Email g863821569@gmail.com
 */
public enum StatusCode {
    SUCCESS(0, "成功"),
    CREATE_USER_FAILED(1, "创建用户失败"),
    SAVE_COUPON_FAILED(2, "保存优惠券失败"),
    USER_NOT_EXIST(3, "用户不存在"),
    COUPON_NOT_EXIST(4, "优惠券不存在"),
    COUPON_STOCK_EMPTY(5, "优惠券库存不够"),
    UPDATE_COUPON_STOCK_FAILED(6, "更新优惠券库存失败"),
    SAVE_USER_COUPONS_FAILED(7, "保存用户优惠券失败"),
    EMPTY_USER_COUPONS(8, "用户领取的优惠券为空"),
    EMPTY_USED_COUPONS(9, "用户使用过的优惠券为空"),
    COUPON_HAS_USED(10, "优惠券已经被使用过了"),
    UPDATE_USER_COUPON_FAILED(11, "更新用户优惠券失败");
    // 状态码
    private int code;
    // 状态信息
    private String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
