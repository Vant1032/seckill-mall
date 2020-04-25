package cc.vant.seckillmall.pojo.user.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class ModifyUserAvatarReq {
    @NotNull
    private MultipartFile file;
}
