package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.pojo.admin.req.AdminLoginReq;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String adminLogin(@Valid AdminLoginReq req) {

        // todo
        return null;
    }
}
