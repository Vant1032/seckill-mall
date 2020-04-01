package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginReq {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
