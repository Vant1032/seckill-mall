package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.UserMapper;
import cc.vant.seckillmall.model.User;
import cc.vant.seckillmall.pojo.user.req.UserSignInReq;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean checkUserLogin(String userName, String password) {
        User entity = new User();
        entity.setUserName(userName);
        User user = userMapper.selectOne(Wrappers.query(entity));
        if (password.equals(user.getPassword()) && user.getStatus()) {
            return true;
        }
        return false;
    }

    public void userSignIn(UserSignInReq req) {
        User entity = new User();
        entity.setUserName(req.getUserName());
        entity.setPassword(req.getPassword());
        entity.setSex(req.getSex());
        entity.setTel(req.getTel());
        userMapper.insert(entity);
    }

    /**
     * @return true 如果名字已存在
     */
    public boolean checkNameExist(String name) {
        User entity = new User();
        entity.setUserName(name);
        User user = userMapper.selectOne(Wrappers.query(entity));
        return user != null;
    }
}
