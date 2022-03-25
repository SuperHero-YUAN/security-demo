package cn.yuanj.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author YuanJ
 * @date 2022/3/19 11:32
 */
@Data
@TableName("menu")
public class Menu {
    @TableId
    private Long menuId;
    private String perms;
}
