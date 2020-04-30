package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserChangePasswordReq {
    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
