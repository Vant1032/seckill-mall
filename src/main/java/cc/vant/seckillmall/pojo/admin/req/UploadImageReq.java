package cc.vant.seckillmall.pojo.admin.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UploadImageReq {
    @NotNull
    private MultipartFile file;
}
