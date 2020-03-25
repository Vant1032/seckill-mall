package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.AdminUserMapper;
import cc.vant.seckillmall.model.AdminUser;
import cc.vant.seckillmall.pojo.admin.req.AdminLoginReq;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    public boolean checkLogin(AdminLoginReq req) {
        String userName = req.getUserName();
        String password = req.getPassword();
        AdminUser entity = new AdminUser();
        entity.setUserName(userName);
        AdminUser adminUser = adminUserMapper.selectOne(new QueryWrapper<>(entity));
        if (adminUser != null && adminUser.getUserName().equals(userName)
        && adminUser.getPassword().equals(password) && adminUser.getStatus()) {
            return true;
        }
        return false;
    }
}
