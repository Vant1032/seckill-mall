package cc.vant.seckillmall.constants;

/**
 * 所有的错误码都将放在这里
 */
public enum  ErrorCode {
    ERROR(1, ""),
    NOT_LOGIN(2, "没有登录")
    ;

    private int code;

    private String msg;


    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
