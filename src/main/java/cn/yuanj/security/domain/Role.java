package cn.yuanj.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author YuanJ
 * @date 2022/3/19 11:32
 */
@Data
@TableName("role")
public class Role {
    @TableId
    private Long roleId;
    private String role;
}
