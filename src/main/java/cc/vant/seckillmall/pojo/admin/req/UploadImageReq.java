package cc.vant.seckillmall.pojo.admin.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadImageReq {

    private MultipartFile file;
}
