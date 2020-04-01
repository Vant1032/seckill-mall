package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.pojo.user.req.UserLoginReq;
import cc.vant.seckillmall.pojo.user.req.UserSignInReq;
import cc.vant.seckillmall.service.UserService;
import cc.vant.seckillmall.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public Response<?> userLogin(HttpSession session, @Valid UserLoginReq req) {
        if (userService.checkUserLogin(req.getUserName(), req.getPassword())) {
            session.setAttribute(Consts.IS_USER_LOGIN, true);
            return Response.success();
        }
        return Response.fail("用户名或密码不匹配");
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Response<?> loginOut(HttpSession session) {
        session.setAttribute(Consts.IS_USER_LOGIN, false);
        return Response.success();
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public Response<?> userSignIn(HttpSession session, @Valid UserSignInReq req) {
        if (userService.checkNameExist(req.getUserName())) {
            return Response.fail("名字已存在");
        }
        userService.userSignIn(req);
        session.setAttribute(Consts.IS_USER_LOGIN, true);
        return Response.success();
    }
}
