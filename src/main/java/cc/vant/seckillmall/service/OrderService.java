package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.OrderInfoMapper;
import cc.vant.seckillmall.mapper.OrderItemMapper;
import cc.vant.seckillmall.model.OrderInfo;
import cc.vant.seckillmall.model.OrderItem;
import cc.vant.seckillmall.pojo.order.req.CreateOrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public Integer createOrder(Integer userId, List<CreateOrderReq.Inner> orders, Integer addrId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setStatus(OrderInfo.Status.WAIT_PAY.name());
        orderInfo.setAddrId(addrId);
        orderInfoMapper.insert(orderInfo);
        Integer orderId = orderInfo.getOrderId();

        for (CreateOrderReq.Inner order : orders) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setGoodsId(order.getGoodsId());
            orderItem.setAmount(order.getAmount());
            orderItemMapper.insert(orderItem);
        }
        return orderId;
    }
}
