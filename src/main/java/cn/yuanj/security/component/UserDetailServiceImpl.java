package cn.yuanj.security.component;

import cn.yuanj.security.domain.User;
import cn.yuanj.security.service.IUserService;
import cn.yuanj.security.service.IUserTokenService;
import cn.yuanj.security.utils.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author YuanJ
 * @date 2022/3/19 11:31
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserTokenService userTokenService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<String> perms = userService.getPrimaryPrincipal(user.getUserId());
        for (String perm : perms) {
            authorities.add(new SimpleGrantedAuthority(perm));
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setPerms(perms);
        loginUser.setUserToken(userTokenService.getById(user.getUserId()));
        redisUtils.set("user:" + username, loginUser);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
