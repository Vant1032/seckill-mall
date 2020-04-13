package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.pojo.admin.req.AdminLoginReq;
import cc.vant.seckillmall.pojo.admin.req.CreateGoodsReq;
import cc.vant.seckillmall.pojo.admin.req.DeleteGoodsReq;
import cc.vant.seckillmall.pojo.admin.req.ModifyGoodsReq;
import cc.vant.seckillmall.pojo.admin.rsp.CreateGoodsRsp;
import cc.vant.seckillmall.service.AdminService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("商家才可以用的功能")
@RestController
@RequestMapping("/api")
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @ApiOperation("商家登录")
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public Response<?> adminLogin(@Valid AdminLoginReq req) {

        if (adminService.isLogin(req)) {
            // 设置session中isLogin为true
            session.setAttribute(Consts.IS_ADMIN_LOGIN, true);
            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }


    @ApiOperation("商家退出的登录")
    @RequestMapping(value = "/admin/loginOut", method = RequestMethod.POST)
    public Response<?> loginOut() {
        session.setAttribute(Consts.IS_ADMIN_LOGIN, false);
        return Response.success();
    }

    @ApiOperation("商家创建秒杀商品")
    @RequestMapping(value = "/admin/createGoods", method = RequestMethod.POST)
    public Response<?> createGoods(@Valid CreateGoodsReq req) {
        Utils.adminLoginCheck(session);

        int goodsId = adminService.createGoods(req);

        CreateGoodsRsp rsp = new CreateGoodsRsp();
        rsp.setGoodsId(goodsId);
        return Response.success(rsp);
    }

    @ApiOperation("商家根据goodId删除商品")
    @RequestMapping(value = "/admin/deleteGoods", method = RequestMethod.POST)
    public Response<?> deleteGoods(@Valid DeleteGoodsReq req) {
        Utils.adminLoginCheck(session);

        adminService.deleteGoods(req);
        return Response.success();
    }

    @ApiOperation("商家修改商品")
    @RequestMapping(value = "/admin/modifyGoods", method = RequestMethod.POST)
    public Response<?> modifyGoods(@Valid ModifyGoodsReq req) {
        Utils.adminLoginCheck(session);

        adminService.modifyGoods(req);
        return Response.success();
    }
}
