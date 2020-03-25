package cc.vant.seckillmall.pojo.admin.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminLoginReq {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
