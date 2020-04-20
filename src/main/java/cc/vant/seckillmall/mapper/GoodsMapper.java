package cc.vant.seckillmall.mapper;

import cc.vant.seckillmall.model.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface GoodsMapper extends BaseMapper<Goods> {
    Page<Goods> getGoodsList(Page<Goods> page,@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
