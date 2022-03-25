package cn.yuanj.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.yuanj.security.domain.User;
import cn.yuanj.security.service.IUserService;
import cn.yuanj.security.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Superhero
 * @date 2021/10/30 12:52
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public Result list() {
        return Result.success(userService.list());
    }

    @GetMapping("/info/{userId}")
    @PreAuthorize("@ps.hasPermi('sys:user:info')")
    public Result info(@PathVariable Integer userId) {
        return Result.success(userService.getOne(new QueryWrapper<User>().eq("user_id", userId)));
    }
}
