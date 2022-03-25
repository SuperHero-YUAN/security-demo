package cn.yuanj.security.utils;

import cn.yuanj.security.component.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YuanJ
 * @date 2022/3/19 21:57
 */
@Component
public class SecurityUtils {

    @Autowired
    private RedisUtils redisUtils;

    public LoginUser getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String username = TokenUtils.getUsername(token);
            return redisUtils.get("user:" + username, LoginUser.class);
        }
        return null;
    }
}
