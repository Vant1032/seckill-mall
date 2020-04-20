package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.constants.Props;
import cc.vant.seckillmall.model.Goods;
import cc.vant.seckillmall.pojo.admin.req.*;
import cc.vant.seckillmall.pojo.admin.rsp.CreateGoodsRsp;
import cc.vant.seckillmall.pojo.admin.rsp.UploadImageRsp;
import cc.vant.seckillmall.service.AdminService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Api("商家才可以用的功能")
@RestController
@RequestMapping("/api")
public class AdminController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private Props props;

    @ApiOperation("商家登录")
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public Response<?> adminLogin(@Valid AdminLoginReq req) {

        if (adminService.isLogin(req)) {
            // 设置session中isLogin为true
            session.setAttribute(Consts.IS_ADMIN_LOGIN, true);
            session.setAttribute(Consts.IS_USER_LOGIN, true);

            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }

    @ApiOperation(value = "检查商家是否登录")
    @RequestMapping(value = "/admin/getLoginStatus", method = RequestMethod.POST)
    public Response<?> getAdminLoginStatus() {
        Utils.adminLoginCheck(session);
        return Response.success();
    }

    @ApiOperation("商家退出的登录")
    @RequestMapping(value = "/admin/loginOut", method = RequestMethod.POST)
    public Response<?> loginOut() {
        session.setAttribute(Consts.IS_ADMIN_LOGIN, false);
        session.setAttribute(Consts.IS_USER_LOGIN, false);
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

    @ApiOperation("商家查看所有已创建的商品")
    @RequestMapping(value = "/admin/getAllGoods", method = RequestMethod.POST)
    public Response<?> getAllGoods() {
        Utils.adminLoginCheck(session);

        List<Goods> allGoods = adminService.getAllGoods();
        return Response.success(allGoods);
    }

    @ApiOperation("上传图片")
    @RequestMapping(value = "/admin/uploadImg", method = RequestMethod.POST)
    public Response<?> uploadImage(@Valid UploadImageReq req) {
        Utils.adminLoginCheck(session);

        String imageName = UUID.randomUUID().toString() + ".jpg";
        Path imagePath = Path.of(props.getImgBasePath(), imageName);
        MultipartFile file = req.getFile();
        if (file == null) {
            return Response.fail("没有图片被上传");
        }
        try {
            file.transferTo(imagePath);
        } catch (IOException e) {
            logger.warn("无法存储上传的图片到本地", e);
            return Response.fail("上传图片失败");
        }
        UploadImageRsp rsp = new UploadImageRsp();
        rsp.setImageName(imageName);
        return Response.success(rsp);
    }
}
