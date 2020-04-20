package cc.vant.seckillmall.pojo.goods.rsp;

import cc.vant.seckillmall.model.Goods;
import lombok.Data;

import java.util.List;

@Data
public class GetGoodsListRsp {

    private Long total;

    private List<Goods> goodsList;
}
