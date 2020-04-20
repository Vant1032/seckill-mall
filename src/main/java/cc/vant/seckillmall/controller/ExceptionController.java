package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.ErrorCode;
import cc.vant.seckillmall.exception.NotLoginException;
import cc.vant.seckillmall.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
@ControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);


    @ExceptionHandler({InvalidParameterException.class})
    public Response<?> invalidParam(InvalidParameterException e) {
        return Response.fail("参数异常:" + e.getMessage());
    }

    @ExceptionHandler({NotLoginException.class})
    public Response<?> notLogin(NotLoginException e) {
        return Response.fail(ErrorCode.NOT_LOGIN);
    }

    @ExceptionHandler({BindException.class})
    public Response<?> bindExceptionHandle(BindException e) {

        StringBuilder sb = new StringBuilder(32);
        sb.append("请求参数异常：");
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            sb.append(fieldName).append(": ").append(message).append(',');
        }
        return Response.fail(sb.toString());
    }

    @ExceptionHandler({Exception.class})
    public Response<?> exceptionHandle(Exception e) {
        logger.error("发生全局异常：", e);
        return Response.fail("服务繁忙");
    }
}
