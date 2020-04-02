package cc.vant.seckillmall.pojo.admin.req;

import cc.vant.seckillmall.model.Goods;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ModifyGoodsReq {
    @NotNull
    @Min(0)
    private Integer goodsId;

    private String goodsName;

    private Double price;

    private Integer amount;

    private String descInfo;

    private Date seckillTime;

    private String imgUrl;

    private Boolean status;


    public Goods toGoods() {
        Goods goods = new Goods();
        goods.setGoodsId(this.goodsId);
        goods.setGoodsName(this.goodsName);
        goods.setPrice(this.price);
        goods.setAmount(this.amount);
        goods.setDescInfo(this.descInfo);
        goods.setSeckillTime(this.seckillTime);
        goods.setImgUrl(this.imgUrl);
        goods.setStatus(this.status);

        // Not mapped Goods fields:
        // createdTime
        // updatedTime
        return goods;
    }
}
