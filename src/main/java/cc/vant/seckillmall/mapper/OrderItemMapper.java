package cc.vant.seckillmall.mapper;

import cc.vant.seckillmall.model.OrderItem;
import cc.vant.seckillmall.pojo.order.model.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
    List<UserOrder> userShowAllOrder(@Param("orderId") Integer orderId);
}
