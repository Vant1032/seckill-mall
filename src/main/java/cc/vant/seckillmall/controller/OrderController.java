package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.pojo.order.req.CreateOrderReq;
import cc.vant.seckillmall.pojo.order.req.UserPayOrderReq;
import cc.vant.seckillmall.pojo.order.req.UserShowAllOrderReq;
import cc.vant.seckillmall.pojo.order.rsp.CreateOrderRsp;
import cc.vant.seckillmall.pojo.order.rsp.UserShowAllOrderRsp;
import cc.vant.seckillmall.service.OrderService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("创建订单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<CreateOrderRsp> createOrder(@Valid @RequestBody CreateOrderReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);

        return orderService.createOrder(userId, req.getOrders(), req.getAddrId());
    }

    @ApiOperation("用户查看所有订单")
    @RequestMapping(value = "/userShowAllOrder", method = RequestMethod.POST)
    public Response<UserShowAllOrderRsp> userShowAllOrder(@Valid @RequestBody UserShowAllOrderReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);
        UserShowAllOrderRsp rsp = orderService.userShowAllOrder(userId);
        return Response.success(rsp);
    }

    @ApiOperation("用户付款订单")
    @RequestMapping(value = "/userPayOrder", method = RequestMethod.POST)
    public Response<?> userPayOrder(@Valid @RequestBody UserPayOrderReq req) {
        Utils.userLoginCheck(session);

        Integer userId = Utils.getUserId(session);
        orderService.userPayOrder(userId, req.getOrderId());
        return Response.success();
    }
}
