package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.exception.NotLoginException;
import cc.vant.seckillmall.pojo.admin.req.AdminLoginReq;
import cc.vant.seckillmall.pojo.admin.req.CreateGoodsReq;
import cc.vant.seckillmall.pojo.admin.req.DeleteGoodsReq;
import cc.vant.seckillmall.pojo.admin.rsp.CreateGoodsRsp;
import cc.vant.seckillmall.service.AdminService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
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
            session.setAttribute(Consts.IS_ADMIN_LOGIN, true);
            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }


    @RequestMapping(value = "/admin/loginOut", method = RequestMethod.POST)
    public Response<?> loginOut(HttpSession session) {
        session.setAttribute(Consts.IS_ADMIN_LOGIN, false);
        return Response.success();
    }

    @RequestMapping(value = "/admin/createGoods", method = RequestMethod.POST)
    public Response<?> createGoods(HttpSession session, CreateGoodsReq req) {
        if (!Utils.adminLogin(session)) {
            throw new NotLoginException();
        }
        int goodsId = adminService.createGoods(req);

        CreateGoodsRsp rsp = new CreateGoodsRsp();
        rsp.setGoodsId(goodsId);
        return Response.success(rsp);
    }

    @RequestMapping(value = "/admin/deleteGoods", method = RequestMethod.POST)
    public Response<?> deleteGoods(HttpSession session, DeleteGoodsReq req) {
        if (!Utils.adminLogin(session)) {
            throw new NotLoginException();
        }
        adminService.deleteGoods(req);
        return Response.success();
    }
}
