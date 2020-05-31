package cc.vant.seckillmall.pojo.order.rsp;

import cc.vant.seckillmall.pojo.order.model.UserOrder;
import lombok.Data;

import java.util.List;

@Data
public class UserShowAllOrderRsp {

    private List<UserOrderInfo> userOrderInfos;

    @Data
    public static class UserOrderInfo {
        private Integer orderId;

        private String status;

        private List<UserOrder> userOrders;
    }
}
