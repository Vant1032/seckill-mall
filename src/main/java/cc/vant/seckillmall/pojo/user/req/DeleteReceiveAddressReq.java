package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DeleteReceiveAddressReq {
    @NotNull
    @Min(0L)
    private Integer addrId;
}
