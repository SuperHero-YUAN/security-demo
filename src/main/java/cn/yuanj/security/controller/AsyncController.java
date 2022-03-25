package cn.yuanj.security.controller;

import cn.yuanj.security.service.IAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YuanJ
 * @date 2022/3/25 10:27
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private IAsyncService asyncService;

    @Async("asyncServiceExecutor")
    @GetMapping("/test")
    public String test() {
        asyncService.testAsync();
        return "async test";
    }

}
