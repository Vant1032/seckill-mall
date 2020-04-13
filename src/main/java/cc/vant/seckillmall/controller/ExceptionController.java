package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.ErrorCode;
import cc.vant.seckillmall.exception.NotLoginException;
import cc.vant.seckillmall.util.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({InvalidParameterException.class})
    public Response<?> invalidParam(InvalidParameterException e) {
        return Response.fail("参数异常:" + e.getMessage());
    }

    @ExceptionHandler({NotLoginException.class})
    public Response<?> notLogin(NotLoginException e) {
        return Response.fail(ErrorCode.NOT_LOGIN);
    }
}
