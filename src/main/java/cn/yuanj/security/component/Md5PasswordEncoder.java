package cn.yuanj.security.component;

import cn.yuanj.security.utils.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author YuanJ
 * @date 2022/3/19 12:32
 */
@Component
public class Md5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return MD5Utils.getMd5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return MD5Utils.getMd5(charSequence.toString()).equals(s);
    }
}
