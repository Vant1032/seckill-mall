package cc.vant.seckillmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 收藏夹
 */
@Data
@TableName(value = "favorites")
public class Favorites {
    @TableId(value = "fav_id", type = IdType.AUTO)
    private Integer favId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 0该收藏夹条目失效，1有效
     */
    @TableField(value = "status")
    private Boolean status;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;
}
