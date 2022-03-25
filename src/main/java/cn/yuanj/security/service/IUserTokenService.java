package cn.yuanj.security.service;

import cn.yuanj.security.domain.UserToken;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.yuanj.security.domain.User;

/**
 * @author YuanJ
 * @date 2022/3/19 20:41
 */
public interface IUserTokenService extends IService<UserToken> {
    int TOKEN_EXPIRE = 3600 * 12;

    /**
     * 创建token
     *
     * @param user
     * @return
     */
    String createToken(User user);
}
