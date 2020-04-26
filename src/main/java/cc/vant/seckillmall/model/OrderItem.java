package cc.vant.seckillmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 订单条目表
 */
@Data
@TableName(value = "order_item")
public class OrderItem {
    @TableId(value = "order_item_id", type = IdType.AUTO)
    private Integer orderItemId;

    @TableField(value = "order_id")
    private Integer orderId;

    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 购买数量
     */
    @TableField(value = "amount")
    private Integer amount;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;
}
