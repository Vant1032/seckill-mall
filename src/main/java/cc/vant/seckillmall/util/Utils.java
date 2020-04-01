package cc.vant.seckillmall.util;

import cc.vant.seckillmall.constants.Consts;

import javax.servlet.http.HttpSession;

/**
 * 比较常用的工具放在这里
 */
public abstract class Utils {

    public static boolean userLogin(HttpSession session) {
        Boolean attribute = (Boolean) session.getAttribute(Consts.IS_USER_LOGIN);
        return attribute != null && attribute;
    }

    public static boolean adminLogin(HttpSession session) {
        Boolean attribute = (Boolean) session.getAttribute(Consts.IS_ADMIN_LOGIN);
        return attribute != null && attribute;
    }
}
