package cc.vant.seckillmall.pojo.order.req;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class UserPayOrderReq {
    @Min(1L)
    private Integer orderId;
}
