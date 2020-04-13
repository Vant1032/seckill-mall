package cc.vant.seckillmall.pojo.goods.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GetGoodsListReq {
    @NotNull
    private Date startTime;

    @NotNull
    private Date endTime;
}
