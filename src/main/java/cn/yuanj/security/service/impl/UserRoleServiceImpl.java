package cn.yuanj.security.service.impl;

import cn.yuanj.security.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.yuanj.security.domain.UserRole;
import cn.yuanj.security.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author YuanJ
 * @date 2022/3/19 11:51
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}
