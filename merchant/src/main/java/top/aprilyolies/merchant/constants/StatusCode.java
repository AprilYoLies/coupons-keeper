package top.aprilyolies.merchant.constants;

/**
 * @Author EvaJohnson
 * @Date 2019-07-29
 * @Email g863821569@gmail.com
 */

/**
 * 状态码枚举类
 */
public enum StatusCode {
    SUCCESS(0, "成功"),
    DUPLICATE_NAME(1, "商户名称重复"),
    EMPTY_LOGO(2, "商户 logo 为空"),
    EMPTY_BUSINESS_LICENSE(3, "商户营业执照为空"),
    ERROR_PHONE(4, "商户联系电话错误"),
    EMPTY_ADDRESS(5, "商户地址为空"),
    MERCHANTS_NOT_EXIST(6, "商户不存在");

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // 状态码
    private int code;
    // 状态说明信息
    private String msg;

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
