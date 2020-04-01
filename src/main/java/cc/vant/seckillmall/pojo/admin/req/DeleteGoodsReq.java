package cc.vant.seckillmall.pojo.admin.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DeleteGoodsReq {
    @NotNull
    @Min(0)
    private Integer goodsId;
}
