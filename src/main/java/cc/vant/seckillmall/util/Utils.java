package cc.vant.seckillmall.util;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.exception.NotLoginException;

import javax.servlet.http.HttpSession;

/**
 * 比较常用的工具放在这里
 */
public abstract class Utils {

    private static boolean userLogin(HttpSession session) {
        Boolean attribute = (Boolean) session.getAttribute(Consts.IS_USER_LOGIN);
        return attribute != null && attribute;
    }

    private static boolean adminLogin(HttpSession session) {
        Boolean attribute = (Boolean) session.getAttribute(Consts.IS_ADMIN_LOGIN);
        return attribute != null && attribute;
    }

    public static void adminLoginException(HttpSession session) {
        if (!Utils.adminLogin(session)) {
            throw new NotLoginException();
        }
    }

    public static void userLoginException(HttpSession session) {
        if (!Utils.userLogin(session)) {
            throw new NotLoginException();
        }
    }
}
