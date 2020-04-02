package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserChangePasswordReq {

    @NotNull
    @Min(0)
    private Integer userId;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
