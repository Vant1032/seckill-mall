package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("测试")
@RestController
public class WelcomeController {

    @ApiOperation("用于测试")
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public Response<?> welcome() {
        return Response.success(null, "hello");
    }
}
