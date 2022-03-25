package cn.yuanj.security.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author YuanJ
 * @date 2022/3/19 11:32
 */
@Data
@TableName("user_role")
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;
}
