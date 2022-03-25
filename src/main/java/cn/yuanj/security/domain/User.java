package cn.yuanj.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author YuanJ
 * @date 2022/3/19 11:32
 */
@Data
@TableName("user")
public class User {
    @TableId
    private Long userId;
    private String username;
    private String password;
    private String slat;
}
