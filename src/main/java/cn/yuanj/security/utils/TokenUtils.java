package cn.yuanj.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Superhero
 * @date 2021/11/24 22:34
 */
public class TokenUtils {
    // 过期时间 5 分钟
    private static final long EXPIRE_TIME = 5*60*1000;

    /**
     * 校验token是否正确
     * @param token token
     * @param username 用户名
     * @param secret 用户密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 根据 token 获取 username
     * @param token token
     * @return token中包含的username
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成 token
     * @param username 用户名
     * @param secret 用户密码
     * @param expire 过期时间
     * @return 加密后的token
     */
    public static String sign(String username, String secret, Date expire) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带 username 信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(expire)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 生成 token
     * @param username 用户名
     * @param secret 用户密码
     * @param expire 过期时间
     * @return 加密后的token
     */
    public static String sign(String username, String secret, long expire) {
        Date date = new Date(System.currentTimeMillis() + expire);
        return sign(username, secret, date);
    }

    /**
     * 生成 token， 默认 5min 后过期
     * @param username 用户名
     * @param secret 用户密码
     * @return 加密后的token
     */
    public static String sign(String username, String secret) {
        return sign(username, secret, EXPIRE_TIME);
    }
}
