package cc.vant.seckillmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
    * 订单信息表
    */
@Data
@TableName(value = "order_info")
public class OrderInfo {
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "addr_id")
    private Integer addrId;

    /**
     * 0代表订单已被取消，1代表待付款，2代表待发货，3代表已收货
     */
    @TableField(value = "status")
    private String status;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;

    /**
     * 用于Status字段的对应数据库的Enum
     */
    public enum Status {
        CANCEL,
        WAIT_PAY,
        WAIT_SHIPPING,
        SHIPPED,
        WAIT_SIGN,
    }
}
