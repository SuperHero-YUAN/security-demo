package cn.yuanj.security.utils;

import java.util.UUID;

/**
 * UUID工具类
 * @author Superhero
 * @date 2021/11/17 23:41
 */
public class UUIDUtils {

    /**
     * 生成带有-的UUID字符串
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成不带-的UUID字符串
     * @return
     */
    public static String simpleUuid() {
        return uuid().replaceAll("-", "");
    }
}
