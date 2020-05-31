package cc.vant.seckillmall.pojo.order.model;

import lombok.Data;

@Data
public class UserOrder {
    private String goodsName;

    private Double price;

    private Integer amount;

    private Integer orderItemId;
}
