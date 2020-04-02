package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeUserInfoReq {
    @NotNull
    private Integer userId;

    private Boolean sex;

    private String tel;
}
