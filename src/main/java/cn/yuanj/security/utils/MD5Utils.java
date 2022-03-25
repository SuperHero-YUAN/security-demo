package cn.yuanj.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5加密工具类
 * @author Superhero
 * @date 2021/11/17 15:58
 */
public class MD5Utils {

    private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    /**
     * md5加密
     * @param source
     * @return
     */
    public static String getMd5(String source) {
        // 1) 使用静态方法，创建MessageDigest对象
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 2) 将字符串使用utf-8进行编码，得到字节数组
            byte[] input = source.getBytes("UTF-8");
            // 3) 使用digest(input)对字节数组进行md5的哈希计算，得到加密以后的字节数组，长度是16个字节。
            byte[] output = md.digest(input);
            // 4) 使用类BigInteger(1, 加密后的字节数组)，将这个二进制数组转成无符号的大整数
            // 1 正数， -1表示负数
            String result = new BigInteger(1, output).toString(16);
            // 5) 将这个大整数转成16进制字符串，参数为多少进制
            for (int i = 0; i < 32 - result.length(); i++) {
                result = "0" + result;
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
