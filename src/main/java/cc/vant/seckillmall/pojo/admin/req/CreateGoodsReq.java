package cc.vant.seckillmall.pojo.admin.req;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateGoodsReq {
    @NotBlank
    private String goodsName;

    @NotNull
    @Min(0)
    private Double price;

    @NotNull
    @Min(0)
    private Integer amount;

    @NotBlank
    private String descInfo;

    @Future
    @NotNull
    private Date seckillTime;

    @NotBlank
    private String imgUrl;
}
