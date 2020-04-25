package cc.vant.seckillmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户账户信息表
 */
@Data
@TableName(value = "`user`")
public class User {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    /**
     * 1代表男,0代表女
     */
    @TableField(value = "sex")
    private Boolean sex;

    /**
     * 手机号
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 0 禁用，1有效
     */
    @TableField(value = "status")
    private Boolean status;

    @TableField(value = "avatar_image_name")
    private String avatarImageName;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;
}
