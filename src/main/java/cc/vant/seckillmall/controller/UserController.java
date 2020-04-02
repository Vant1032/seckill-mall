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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Api("跟用户相关的")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login")
    public Response<?> userLogin(HttpSession session, @Valid UserLoginReq req) {
        if (userService.checkUserLogin(req.getUserName(), req.getPassword())) {
            session.setAttribute(Consts.IS_USER_LOGIN, true);
            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }

    @ApiOperation("用户退出登录")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Response<?> loginOut(HttpSession session) {
        session.setAttribute(Consts.IS_USER_LOGIN, false);
        return Response.success();
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public Response<?> userSignIn(HttpSession session, @Valid UserSignInReq req) {
        if (userService.checkNameExist(req.getUserName())) {
            return Response.fail("名字已存在");
        }
        userService.userSignIn(req);
        session.setAttribute(Consts.IS_USER_LOGIN, true);
        return Response.success();
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Response<?> userChangePassword(HttpSession session, @Valid UserChangePasswordReq req) {
        Utils.adminLoginException(session);

        return userService.userChangePassword(req);
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    public Response<?> changeUserInfo(HttpSession session, ChangeUserInfoReq req) {
        Utils.adminLoginException(session);

        // todo
    }


    @ApiOperation("查看收藏夹")
    @RequestMapping(value = "/viewFavorites", method = RequestMethod.GET)
    public Response<?> viewFavorites(HttpSession session, @Valid ViewFavoritesReq req) {
        Utils.adminLoginException(session);

        ViewFavoritesRsp rsp = userService.viewFavorites(req);
        return Response.success(rsp);
    }

    @ApiOperation("添加商品到收藏夹")
    @RequestMapping(value = "/addToFavorites", method = RequestMethod.POST)
    public Response<?> addToFavorites(HttpSession session, @Valid AddToFavoritesReq req) {
        Utils.adminLoginException(session);

        // 检查是否已存在
        if (userService.checkFavoritesGoodsExist(req.getGoodsId())) {
            return Response.fail("商品已经加入收藏夹");
        }
        userService.addToFavorites(req);
        return Response.success();
    }

    @ApiOperation("删除收藏夹中的商品")
    @RequestMapping(value = "/deleteFavorites")
    public Response<?> deleteFavorites(HttpSession session, @Valid DeleteFavoritesReq req) {
        Utils.adminLoginException(session);

        if (!userService.checkFavoritesIdExist(req.getFavId())) {
            return Response.fail("请求参数错误");
        }
        userService.deleteFavorites(req);
        return Response.success();
    }


}
