package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.util.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping("/welcome")
    public Response<?> welcome() {
        return Response.success(null, "hello");
    }
}
