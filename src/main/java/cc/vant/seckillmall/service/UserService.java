package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.FavoritesMapper;
import cc.vant.seckillmall.mapper.ReceiveAddressMapper;
import cc.vant.seckillmall.mapper.UserMapper;
import cc.vant.seckillmall.model.Favorites;
import cc.vant.seckillmall.model.ReceiveAddress;
import cc.vant.seckillmall.model.User;
import cc.vant.seckillmall.pojo.user.req.*;
import cc.vant.seckillmall.pojo.user.rsp.ViewFavoritesRsp;
import cc.vant.seckillmall.util.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FavoritesMapper favoritesMapper;

    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

    public User getUserByUserName(String userName) {
        User entity = new User();
        entity.setUserName(userName);
        return userMapper.selectOne(Wrappers.query(entity));
    }

    public Integer userSignIn(UserSignInReq req) {
        User entity = new User();
        entity.setUserName(req.getUserName());
        entity.setPassword(req.getPassword());
        entity.setSex(req.getSex());
        entity.setTel(req.getTel());
        userMapper.insert(entity);
        return entity.getUserId();
    }

    /**
     * @return true 如果名字已存在
     */
    public boolean isNameExist(String name) {
        User entity = new User();
        entity.setUserName(name);
        User user = userMapper.selectOne(Wrappers.query(entity));
        return user != null;
    }

    public void addToFavorites(AddToFavoritesReq req) {
        Favorites entity = req.toFavorites();
        favoritesMapper.insert(entity);
    }

    /**
     * 检查商品是否已经加入到收藏夹了
     */
    public boolean isFavoritesGoodsExist(Integer goodsId) {
        Favorites entity = new Favorites();
        entity.setGoodsId(goodsId);
        Favorites rst = favoritesMapper.selectOne(Wrappers.query(entity));
        return rst != null;
    }

    public ViewFavoritesRsp viewFavorites(ViewFavoritesReq req) {
        Favorites entity = new Favorites();
        entity.setUserId(req.getUserId());
        entity.setStatus(Boolean.TRUE);
        List<Favorites> favorites = favoritesMapper.selectList(Wrappers.query(entity));
        ViewFavoritesRsp rsp = new ViewFavoritesRsp();
        rsp.setFavoritesList(favorites);
        return rsp;
    }

    public boolean isFavoritesIdExist(Integer favId) {
        Favorites favorites = favoritesMapper.selectById(favId);
        return favorites != null;
    }

    public void deleteFavorites(DeleteFavoritesReq req) {
        favoritesMapper.deleteById(req.getFavId());
    }

    public Response<?> userChangePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Response.fail("userId不合法");
        }
        if (user.getPassword().equals(oldPassword)) {
            User entity = new User();
            entity.setUserId(userId);
            entity.setPassword(newPassword);
            userMapper.updateById(entity);
            return Response.success();
        }
        return Response.fail("密码错误");
    }

    public void createReceiveAddress(CreateReceiveAddressReq req, Integer userId) {
        Boolean defaultAddr = ObjectUtils.defaultIfNull(req.getDefaultAddr(), Boolean.FALSE);
        if (defaultAddr) {
            receiveAddressMapper.clearDefaultAddr(userId);
        }
        ReceiveAddress receiveAddress = req.toReceiveAddress();
        receiveAddress.setUserId(userId);
        receiveAddressMapper.insert(receiveAddress);
    }

    public List<ReceiveAddress> getAllReceiveAddress(Integer userId) {
        ReceiveAddress receiveAddress = new ReceiveAddress();
        receiveAddress.setUserId(userId);
        return receiveAddressMapper.selectList(new QueryWrapper<>(receiveAddress));
    }

    public void deleteReceiveAddress(Integer userId, Integer addrId) {
        ReceiveAddress receiveAddress = new ReceiveAddress();
        receiveAddress.setUserId(userId);
        receiveAddress.setAddrId(addrId);
        receiveAddressMapper.delete(new QueryWrapper<>(receiveAddress));
    }

    public String getUserAvatar(Integer userId) {
        User user = userMapper.selectById(userId);
        return user.getAvatarImageName();
    }

    public void modifyUserAvatar(Integer userId, String imageName) {
        User entity = new User();
        entity.setUserId(userId);
        entity.setAvatarImageName(imageName);
        userMapper.updateById(entity);
    }
}
