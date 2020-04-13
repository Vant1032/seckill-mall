package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.GoodsMapper;
import cc.vant.seckillmall.model.Goods;
import cc.vant.seckillmall.pojo.goods.req.GetGoodsListReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getGoodsList(GetGoodsListReq req) {
        return goodsMapper.getGoodsList(req.getStartTime(), req.getEndTime());
    }
}
