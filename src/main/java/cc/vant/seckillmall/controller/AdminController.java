package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.pojo.admin.req.AdminLoginReq;
import cc.vant.seckillmall.service.AdminService;
import cc.vant.seckillmall.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public Response<?> adminLogin(HttpSession session, @Valid AdminLoginReq req) {

        if (adminService.checkLogin(req)) {
            // 设置session中isLogin为true
            session.setAttribute("isLogin", true);
            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }
}
