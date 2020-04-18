package cc.vant.seckillmall.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Props {
    @Value("${img.basePath}")
    private String imgBasePath;

}
