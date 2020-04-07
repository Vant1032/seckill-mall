package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.AdminUserMapper;
import cc.vant.seckillmall.mapper.GoodsMapper;
import cc.vant.seckillmall.model.AdminUser;
import cc.vant.seckillmall.model.Goods;
import cc.vant.seckillmall.pojo.admin.req.AdminLoginReq;
import cc.vant.seckillmall.pojo.admin.req.CreateGoodsReq;
import cc.vant.seckillmall.pojo.admin.req.DeleteGoodsReq;
import cc.vant.seckillmall.pojo.admin.req.ModifyGoodsReq;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    public boolean isLogin(AdminLoginReq req) {
        String userName = req.getUserName();
        String password = req.getPassword();
        AdminUser entity = new AdminUser();
        entity.setUserName(userName);
        AdminUser adminUser = adminUserMapper.selectOne(new QueryWrapper<>(entity));
        return adminUser != null && adminUser.getUserName().equals(userName)
                && adminUser.getPassword().equals(password) && adminUser.getStatus();
    }

    public int createGoods(CreateGoodsReq req) {
        Goods entity = new Goods();
        entity.setGoodsName(req.getGoodsName());
        entity.setPrice(req.getPrice());
        entity.setAmount(req.getAmount());
        entity.setDescInfo(req.getDescInfo());
        entity.setSeckillTime(req.getSeckillTime());
        entity.setImgUrl(req.getImgUrl());
        return goodsMapper.insert(entity);
    }

    public void deleteGoods(DeleteGoodsReq req) {
        goodsMapper.deleteById(req.getGoodsId());
    }

    public void modifyGoods(ModifyGoodsReq req) {
        Goods entity = req.toGoods();
        goodsMapper.updateById(entity);
    }

}
