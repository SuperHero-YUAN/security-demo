package cn.yuanj.security.service.impl;

import cn.yuanj.security.domain.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.yuanj.security.mapper.RoleMapper;
import cn.yuanj.security.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @author YuanJ
 * @date 2022/3/19 11:52
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
