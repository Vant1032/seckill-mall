package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.pojo.user.req.*;
import cc.vant.seckillmall.pojo.user.rsp.ViewFavoritesRsp;
import cc.vant.seckillmall.service.UserService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api("跟用户相关的")
@RequestMapping("/api/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<?> userLogin(@Valid UserLoginReq req) {
        if (userService.isUserLogin(req.getUserName(), req.getPassword())) {
            session.setAttribute(Consts.IS_USER_LOGIN, Boolean.TRUE);
            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }

    @ApiOperation("用户退出登录")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Response<?> loginOut() {
        session.setAttribute(Consts.IS_USER_LOGIN, false);
        return Response.success();
    }

    @ApiOperation(value = "检查用户是否登录")
    @RequestMapping(value = "/getLoginStatus", method = RequestMethod.POST)
    public Response<?> getAdminLoginStatus() {
        Utils.userLoginCheck(session);
        return Response.success();
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public Response<?> userSignIn(@Valid UserSignInReq req) {
        if (userService.isNameExist(req.getUserName())) {
            return Response.fail("名字已存在");
        }
        userService.userSignIn(req);
        session.setAttribute(Consts.IS_USER_LOGIN, true);
        return Response.success();
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Response<?> userChangePassword(@Valid UserChangePasswordReq req) {
        Utils.adminLoginCheck(session);

        return userService.userChangePassword(req);
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    public Response<?> changeUserInfo(@Valid ChangeUserInfoReq req) {
        Utils.adminLoginCheck(session);


        // todo
        return null;
    }


    @ApiOperation(value = "查看收藏夹", notes = "会分页")
    @RequestMapping(value = "/viewFavorites", method = RequestMethod.GET)
    public Response<?> viewFavorites(@Valid ViewFavoritesReq req) {
        Utils.adminLoginCheck(session);

        ViewFavoritesRsp rsp = userService.viewFavorites(req);
        // todo分页
        return Response.success(rsp);
    }

    @ApiOperation("添加商品到收藏夹")
    @RequestMapping(value = "/addToFavorites", method = RequestMethod.POST)
    public Response<?> addToFavorites(@Valid AddToFavoritesReq req) {
        Utils.adminLoginCheck(session);

        // 检查是否已存在
        if (userService.isFavoritesGoodsExist(req.getGoodsId())) {
            return Response.fail("商品已经加入收藏夹");
        }
        userService.addToFavorites(req);
        return Response.success();
    }

    @ApiOperation("删除收藏夹中的商品")
    @RequestMapping(value = "/deleteFavorites", method = RequestMethod.POST)
    public Response<?> deleteFavorites(@Valid DeleteFavoritesReq req) {
        Utils.adminLoginCheck(session);

        if (!userService.isFavoritesIdExist(req.getFavId())) {
            return Response.fail("请求参数错误");
        }
        userService.deleteFavorites(req);
        return Response.success();
    }


}
