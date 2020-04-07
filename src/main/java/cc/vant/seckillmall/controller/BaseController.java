package cc.vant.seckillmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public abstract class BaseController {
    @Autowired
    protected HttpSession session;
}
