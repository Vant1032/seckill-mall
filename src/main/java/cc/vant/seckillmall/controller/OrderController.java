package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.constants.Consts;
import cc.vant.seckillmall.pojo.order.req.CreateOrderReq;
import cc.vant.seckillmall.pojo.order.rsp.CreateOrderRsp;
import cc.vant.seckillmall.service.OrderService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response<?> createOrder(@Valid CreateOrderReq req) {
        Utils.userLoginCheck(session);

        Integer userId = (Integer) session.getAttribute(Consts.USER_ID);
        Integer orderId = orderService.createOrder(userId, req.getOrders(), req.getAddrId());
        CreateOrderRsp rsp = new CreateOrderRsp();
        rsp.setOrderId(orderId);
        return Response.success(rsp);
    }
}
