package cn.yuanj.security.controller;

import cn.yuanj.security.service.IUserService;
import cn.yuanj.security.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YuanJ
 * @date 2022/3/19 20:27
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result login(String username, String password) {
        String token = userService.login(username, password);
        return Result.success("登录成功", token);
    }

}
