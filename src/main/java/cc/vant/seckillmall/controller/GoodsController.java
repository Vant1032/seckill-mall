package cc.vant.seckillmall.controller;

import cc.vant.seckillmall.model.Goods;
import cc.vant.seckillmall.pojo.goods.req.GetGoodsByIdReq;
import cc.vant.seckillmall.pojo.goods.req.GetGoodsListReq;
import cc.vant.seckillmall.pojo.goods.rsp.GetGoodsListRsp;
import cc.vant.seckillmall.service.GoodsService;
import cc.vant.seckillmall.util.Response;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("商品")
@RestController
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("获取某段时间内的秒杀商品")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public Response<?> getGoodsList(@Valid @RequestBody GetGoodsListReq req) {
        Page<Goods> page = goodsService.getGoodsListPage(req);
        GetGoodsListRsp rsp = new GetGoodsListRsp();
        rsp.setGoodsList(page.getRecords());
        rsp.setTotal(page.getTotal());
        return Response.success(rsp);
    }

    @ApiOperation("通过goodID获取秒杀商品")
    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST)
    public Response<?> getGoodsById(@Valid @RequestBody GetGoodsByIdReq req) {
        Goods goods = goodsService.getGoodsById(req.getGoodsId());
        if (goods == null) {
            return Response.fail("相应商品不存在");
        }
        return Response.success(goods);
    }
}
