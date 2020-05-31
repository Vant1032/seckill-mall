package cc.vant.seckillmall.service;

import cc.vant.seckillmall.mapper.GoodsMapper;
import cc.vant.seckillmall.mapper.OrderInfoMapper;
import cc.vant.seckillmall.mapper.OrderItemMapper;
import cc.vant.seckillmall.model.Goods;
import cc.vant.seckillmall.model.OrderInfo;
import cc.vant.seckillmall.model.OrderItem;
import cc.vant.seckillmall.pojo.order.model.UserOrder;
import cc.vant.seckillmall.pojo.order.req.CreateOrderReq;
import cc.vant.seckillmall.pojo.order.rsp.CreateOrderRsp;
import cc.vant.seckillmall.pojo.order.rsp.UserShowAllOrderRsp;
import cc.vant.seckillmall.util.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    public Response<CreateOrderRsp> createOrder(Integer userId, List<CreateOrderReq.Inner> orders, Integer addrId) {
        for (CreateOrderReq.Inner order : orders) {
            // 检查库存
            Goods goods = goodsMapper.selectById(order.getGoodsId());
            if (goods.getSeckillTime().after(new Date())) {
                return Response.fail("未到秒杀时间");
            }
            if (goods.getAmount() >= order.getAmount()) {
                Goods entity = new Goods();
                entity.setGoodsId(order.getGoodsId());
                entity.setAmount(goods.getAmount() - order.getAmount());
                goodsMapper.updateById(entity);
            } else {
                return Response.fail("库存不足");
            }
        }

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

        CreateOrderRsp rsp = new CreateOrderRsp();
        rsp.setOrderId(orderId);

        return Response.success(rsp);
    }

    public UserShowAllOrderRsp userShowAllOrder(Integer userId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(new QueryWrapper<>(orderInfo));

        List<UserShowAllOrderRsp.UserOrderInfo> userOrderInfos = new ArrayList<>();
        for (OrderInfo info : orderInfos) {
            // 目前是只能查出一个订单条目
            List<UserOrder> userOrders = orderItemMapper.userShowAllOrder(info.getOrderId());
            UserShowAllOrderRsp.UserOrderInfo order = new UserShowAllOrderRsp.UserOrderInfo();
            order.setStatus(info.getStatus());
            order.setOrderId(info.getOrderId());
            order.setUserOrders(userOrders);
            userOrderInfos.add(order);
        }

        UserShowAllOrderRsp rsp = new UserShowAllOrderRsp();
        rsp.setUserOrderInfos(userOrderInfos);

        return rsp;
    }

    public UserShowAllOrderRsp showOrder(String status) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setStatus(status);
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(new QueryWrapper<>(orderInfo));

        List<UserShowAllOrderRsp.UserOrderInfo> userOrderInfos = new ArrayList<>();
        for (OrderInfo info : orderInfos) {
            // 目前是只能查出一个订单条目
            List<UserOrder> userOrders = orderItemMapper.userShowAllOrder(info.getOrderId());
            UserShowAllOrderRsp.UserOrderInfo order = new UserShowAllOrderRsp.UserOrderInfo();
            order.setStatus(info.getStatus());
            order.setOrderId(info.getOrderId());
            order.setUserOrders(userOrders);
            userOrderInfos.add(order);
        }

        UserShowAllOrderRsp rsp = new UserShowAllOrderRsp();
        rsp.setUserOrderInfos(userOrderInfos);

        return rsp;
    }

    public void userPayOrder(Integer userId, Integer orderId) {
        OrderInfo entity = new OrderInfo();
        entity.setUserId(userId);
        entity.setOrderId(orderId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setStatus(OrderInfo.Status.WAIT_SHIPPING.name());
        orderInfoMapper.update(orderInfo, new UpdateWrapper<>(entity));
    }

    public void deliverGoods(Integer orderId) {
        OrderInfo entity = new OrderInfo();
        entity.setOrderId(orderId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setStatus(OrderInfo.Status.WAIT_SIGN.name());
        orderInfoMapper.update(orderInfo, new UpdateWrapper<>(entity));
    }
}
