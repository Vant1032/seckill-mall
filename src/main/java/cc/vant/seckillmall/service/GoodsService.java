package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.GoodsMapper;
import cc.vant.seckillmall.model.Goods;
import cc.vant.seckillmall.pojo.goods.req.GetGoodsListReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public Page<Goods> getGoodsListPage(GetGoodsListReq req) {
        return goodsMapper.getGoodsList(
                new Page<>(req.getCurrentPage(), req.getPageSize()), req.getStartTime(), req.getEndTime());
    }

    public Goods getGoodsById(Integer goodsId) {
        return goodsMapper.selectById(goodsId);
    }
}
