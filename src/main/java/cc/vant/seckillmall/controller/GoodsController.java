package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.model.Goods;
import cc.vant.seckillmall.pojo.goods.req.GetGoodsListReq;
import cc.vant.seckillmall.service.GoodsService;
import cc.vant.seckillmall.util.Response;
import cc.vant.seckillmall.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api("商品")
@RestController
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("获取某段时间内的秒杀商品")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public Response<?> getGoodsList(@Valid GetGoodsListReq req) {
        Utils.userLoginCheck(session);

        List<Goods> goodsList = goodsService.getGoodsList(req);
        return Response.success(goodsList);
    }
}
