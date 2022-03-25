package cn.yuanj.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.yuanj.security.domain.RoleMenu;
import cn.yuanj.security.mapper.RoleMenuMapper;
import cn.yuanj.security.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author YuanJ
 * @date 2022/3/19 11:53
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
}
