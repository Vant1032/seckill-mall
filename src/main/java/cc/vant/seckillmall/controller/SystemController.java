package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.util.Response;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("系统")
@RestController
@RequestMapping("/api")
public class SystemController {


    @ApiOperation(value = "开发时清除缓存", notes = "用于开发使用JRebel时清除FastJson的对象解析Cache")
    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    public Response<?> clearCache() {
        SerializeConfig.getGlobalInstance().clearSerializers();
        ParserConfig.getGlobalInstance().clearDeserializers();
        return Response.success();
    }
}
