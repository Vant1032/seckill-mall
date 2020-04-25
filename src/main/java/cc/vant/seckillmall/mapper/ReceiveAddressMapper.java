package cc.vant.seckillmall.mapper;

import cc.vant.seckillmall.model.ReceiveAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ReceiveAddressMapper extends BaseMapper<ReceiveAddress> {
    int clearDefaultAddr(Integer userId);
}
