package cc.vant.seckillmall.mapper;

import cc.vant.seckillmall.model.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> getGoodsList(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
