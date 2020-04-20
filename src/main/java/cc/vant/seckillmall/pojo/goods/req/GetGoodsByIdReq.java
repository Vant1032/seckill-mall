package cc.vant.seckillmall.pojo.goods.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class GetGoodsByIdReq {
    @Min(0)
    @NotNull
    private Integer goodsId;
}
