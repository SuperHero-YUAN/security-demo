package cn.yuanj.security.service;

import cn.yuanj.security.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * @author YuanJ
 * @date 2022/3/19 11:48
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取权限列表
     *
     * @param userId
     * @return
     */
    Set<String> getPrimaryPrincipal(Long userId);
}
