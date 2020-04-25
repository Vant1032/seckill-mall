package cc.vant.seckillmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
    * 收货地址表
    */
@Data
@TableName(value = "receive_address")
public class ReceiveAddress {
    @TableId(value = "addr_id", type = IdType.AUTO)
    private Integer addrId;

    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 收件人姓名
     */
    @TableField(value = "receiver_name")
    private String receiverName;

    @TableField(value = "province")
    private String province;

    @TableField(value = "city")
    private String city;

    @TableField(value = "county")
    private String county;

    @TableField(value = "detail_addr")
    private String detailAddr;

    /**
     * 收件人手机号
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 是否是默认收货地址
     */
    @TableField(value = "default_addr")
    private Boolean defaultAddr;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;
}
