package cn.yuanj.security.utils;

import cn.yuanj.security.component.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author YuanJ
 * @date 2022/3/20 0:51
 */
@Service("ps")
public class PermissionService {

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    @Autowired
    private SecurityUtils securityUtils;

    public boolean hasPermi(String permission) {
        if (permission == null || permission.trim().length() <= 0) {
            return false;
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LoginUser user = securityUtils.getUser(request);
        if (user == null || CollectionUtils.isEmpty(user.getPerms())) {
            return false;
        }
        return hasPermissions(user.getPerms(), permission);
    }

    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(permission.trim());
    }
}
