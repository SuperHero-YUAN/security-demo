package cn.yuanj.security.service.impl;

import cn.yuanj.security.domain.UserToken;
import cn.yuanj.security.mapper.UserTokenMapper;
import cn.yuanj.security.service.IUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.yuanj.security.domain.User;
import cn.yuanj.security.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author YuanJ
 * @date 2022/3/19 20:41
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements IUserTokenService {

    /**
     * 创建token
     *
     * @param user
     * @return
     */
    @Override
    public String createToken(User user) {
        // 生成时间
        Date now = new Date();
        // 过期时间
        Date expireTime = new Date(now.getTime() + TOKEN_EXPIRE * 1000);
        // 生成token
        String token = TokenUtils.sign(user.getUsername(), user.getPassword(), expireTime);

        // 创建token对象并保存到数据库
        UserToken userToken = new UserToken();
        userToken.setUserId(user.getUserId());
        userToken.setToken(token);
        userToken.setExpireTime(expireTime);
        userToken.setUpdateTime(now);
        this.saveOrUpdate(userToken);
        return token;
    }
}
