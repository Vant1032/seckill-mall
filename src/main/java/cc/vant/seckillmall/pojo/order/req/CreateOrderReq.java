package cc.vant.seckillmall.pojo.order.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderReq {
    @NotNull
    @Min(0L)
    private Integer addrId;

    private List<Inner> orders;

    @Data
    public static class Inner {
        @NotNull
        @Min(0L)
        private Integer goodsId;

        @NotNull
        @Min(1L)
        private Integer amount;
    }
}
