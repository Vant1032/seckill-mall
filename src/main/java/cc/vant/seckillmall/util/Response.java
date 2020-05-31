package cc.vant.seckillmall.util;

import cc.vant.seckillmall.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

    private int code;

    private T data;

    private String msg;

    public static Response<?> success() {
        return new Response<>(0, null, "");
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(0, data, "");
    }

    public static <T> Response<T> success(T data, String msg) {
        return new Response<>(0, data, msg);
    }

    public static <T> Response<T> fail() {
        return new Response<>(1, null, "");
    }

    public static <T> Response<T> fail(String msg) {
        return new Response<>(1, null, msg);
    }

    public static <T> Response<T> fail(ErrorCode errorCode) {
        return new Response<>(errorCode.getCode(), null, errorCode.getMsg());
    }
}
