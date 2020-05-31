package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.constants.Props;
import cc.vant.seckillmall.model.User;
import cc.vant.seckillmall.pojo.user.req.*;
import cc.vant.seckillmall.pojo.user.rsp.GetUserAvatarRsp;
import cc.vant.seckillmall.pojo.user.rsp.ModifyUserAvatarRsp;
import cc.vant.seckillmall.pojo.user.rsp.ViewFavoritesRsp;
import cc.vant.seckillmall.service.UserService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;


@Api("跟用户相关的")
@RequestMapping("/api/user")
@RestController
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Props props;

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<?> userLogin(@Valid @RequestBody UserLoginReq req) {
        User user = userService.getUserByUserName(req.getUserName());
        if (user != null && req.getUserName().equals(user.getUserName()) && req.getPassword().equals(user.getPassword())) {
            session.setAttribute(Consts.USER_ID, user.getUserId());
            session.setAttribute(Consts.IS_USER_LOGIN, Boolean.TRUE);
            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }

    @ApiOperation("用户退出登录")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Response<?> loginOut() {
        session.removeAttribute(Consts.USER_ID);
        session.removeAttribute(Consts.IS_USER_LOGIN);
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
    public Response<?> userSignIn(@Valid @RequestBody UserSignInReq req) {
        if (userService.isNameExist(req.getUserName())) {
            return Response.fail("名字已存在");
        }
        Integer userId = userService.userSignIn(req);
        session.setAttribute(Consts.IS_USER_LOGIN, true);
        session.setAttribute(Consts.USER_ID, userId);
        return Response.success();
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Response<?> userChangePassword(@Valid @RequestBody UserChangePasswordReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);
        return userService.userChangePassword(userId, req.getOldPassword(), req.getNewPassword());
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    public Response<?> changeUserInfo(@Valid @RequestBody ChangeUserInfoReq req) {
        Utils.userLoginCheck(session);


        // todo
        return null;
    }

    @ApiOperation("获取用户头像图片名")
    @RequestMapping(value = "/getAvatar", method = RequestMethod.POST)
    public Response<?> getUserAvatar(@Valid @RequestBody GetUserAvatarReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);
        String avatar = userService.getUserAvatar(userId);
        GetUserAvatarRsp rsp = new GetUserAvatarRsp();
        rsp.setAvatarImageName(avatar);
        return Response.success(rsp);
    }

    @ApiOperation("修改用户头像")
    @RequestMapping(value = "/modifyUserAvatar", method = RequestMethod.POST)
    public Response<?> modifyUserAvatar(@Valid ModifyUserAvatarReq req) {
        Utils.userLoginCheck(session);

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
        Integer userId = Utils.getUserId(session);
        userService.modifyUserAvatar(userId, imageName);
        ModifyUserAvatarRsp rsp = new ModifyUserAvatarRsp();
        rsp.setImageName(imageName);
        return Response.success(rsp);
    }


    @ApiOperation(value = "查看收藏夹", notes = "会分页")
    @RequestMapping(value = "/viewFavorites", method = RequestMethod.GET)
    public Response<?> viewFavorites(@Valid @RequestBody ViewFavoritesReq req) {
        Utils.userLoginCheck(session);

        ViewFavoritesRsp rsp = userService.viewFavorites(req);
        // todo分页
        return Response.success(rsp);
    }

    @ApiOperation("添加商品到收藏夹")
    @RequestMapping(value = "/addToFavorites", method = RequestMethod.POST)
    public Response<?> addToFavorites(@Valid @RequestBody AddToFavoritesReq req) {
        Utils.userLoginCheck(session);

        // 检查是否已存在
        if (userService.isFavoritesGoodsExist(req.getGoodsId())) {
            return Response.fail("商品已经加入收藏夹");
        }
        userService.addToFavorites(req);
        return Response.success();
    }

    @ApiOperation("删除收藏夹中的商品")
    @RequestMapping(value = "/deleteFavorites", method = RequestMethod.POST)
    public Response<?> deleteFavorites(@Valid @RequestBody DeleteFavoritesReq req) {
        Utils.userLoginCheck(session);

        if (!userService.isFavoritesIdExist(req.getFavId())) {
            return Response.fail("请求参数错误");
        }
        userService.deleteFavorites(req);
        return Response.success();
    }

    @ApiOperation("买家增加收货地址")
    @RequestMapping(value = "/createReceiveAddress", method = RequestMethod.POST)
    public Response<?> createReceiveAddress(@Valid @RequestBody CreateReceiveAddressReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);
        userService.createReceiveAddress(req, userId);

        return Response.success();
    }

    @ApiOperation("查找买家所有的收货地址")
    @RequestMapping(value = "/getAllReceiveAddress", method = RequestMethod.POST)
    public Response<?> getAllReceiveAddress(@Valid @RequestBody GetAllReceiveAddressReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);
        return Response.success(userService.getAllReceiveAddress(userId));
    }

    @ApiOperation("买家删除收货地址")
    @RequestMapping(value = "/deleteReceiveAddress", method = RequestMethod.POST)
    public Response<?> deleteReceiveAddress(@Valid @RequestBody DeleteReceiveAddressReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);
        userService.deleteReceiveAddress(userId, req.getAddrId());
        return Response.success();
    }
}
