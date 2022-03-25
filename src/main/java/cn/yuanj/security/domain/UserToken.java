package cn.yuanj.security.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author YuanJ
 * @date 2022/3/19 20:38
 */
@Data
@TableName("user_token")
public class UserToken {
    @TableId(type = IdType.INPUT)
    private Long userId;
    private String token;
    private Date expireTime;
    private Date updateTime;
}
