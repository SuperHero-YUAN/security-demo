package cn.yuanj.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.yuanj.security.domain.Menu;
import cn.yuanj.security.mapper.MenuMapper;
import cn.yuanj.security.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * @author YuanJ
 * @date 2022/3/19 11:54
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
}
