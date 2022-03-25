package cn.yuanj.security.service.impl;

import cn.yuanj.security.service.IUserTokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.yuanj.security.domain.User;
import cn.yuanj.security.mapper.UserMapper;
import cn.yuanj.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author YuanJ
 * @date 2022/3/19 11:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private IUserTokenService userTokenService;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        User dbUser = this.getOne(new QueryWrapper<User>().eq("username", username));
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dbUser.getUsername(), dbUser.getUsername() + password + dbUser.getSlat()));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new RuntimeException("BadCredentialsException");
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
        return userTokenService.createToken(dbUser);
    }

    /**
     * 获取权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getPrimaryPrincipal(Long userId) {
        List<String> permsList = baseMapper.getPrimaryPrincipal(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    private static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }
}
