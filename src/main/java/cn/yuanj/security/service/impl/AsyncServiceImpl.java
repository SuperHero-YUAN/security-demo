package cn.yuanj.security.service.impl;

import cn.yuanj.security.service.IAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author YuanJ
 * @date 2022/3/25 10:26
 */
@Service
public class AsyncServiceImpl implements IAsyncService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 测试
     */
    @Override
    public void testAsync() {
        try{
            Thread.sleep(10000);
            logger.info("start executeAsync");
            System.out.println("测试异步任务！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }
}
