package cc.vant.seckillmall.pojo.goods.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GetGoodsListReq {

    @NotNull
    @Min(1)
    private Integer currentPage;

    @NotNull
    @Min(1)
    private Integer pageSize;

    @NotNull
    private Date startTime;

    @NotNull
    private Date endTime;
}
