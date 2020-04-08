package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;

@Data
public class UserSignInReq {
//    @NotBlank
    private String userName;

//    @NotBlank
    private String password;

//    @NotNull
    private Boolean sex;

    private String tel;
}
