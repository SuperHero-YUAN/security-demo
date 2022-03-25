package cn.yuanj.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author YuanJ
 * @date 2022/3/19 11:32
 */
@Data
@TableName("role_menu")
public class RoleMenu {
    @TableId
    private Long id;
    private Long roleId;
    private Long menuId;
}
