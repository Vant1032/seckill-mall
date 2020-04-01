package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserSignInReq {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotNull
    private Boolean sex;

    private String tel;
}
