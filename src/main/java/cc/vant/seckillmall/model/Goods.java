package cc.vant.seckillmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
    * 商品信息表
    */
@Data
@TableName(value = "goods")
public class Goods {
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品名
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 商品价格
     */
    @TableField(value = "price")
    private Double price;

    /**
     * 数量
     */
    @TableField(value = "amount")
    private Integer amount;

    /**
     * 商品描述信息
     */
    @TableField(value = "desc_info")
    private String descInfo;

    /**
     * 秒杀时间
     */
    @TableField(value = "seckill_time")
    private Date seckillTime;

    /**
     * 商品图片url地址
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 0下架，1上架
     */
    @TableField(value = "status")
    private Boolean status;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;

}
