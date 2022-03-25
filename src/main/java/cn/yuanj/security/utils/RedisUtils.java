package cn.yuanj.security.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Superhero
 * @date 2021/11/22 23:37
 */
@Component
public class RedisUtils {
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;
    private final static Gson gson = new Gson();

    // ============== string =====================

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public Object getBy(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public int len(String prefix) {
        return keys(prefix).size();
    }

    public Set<String> keys(String prefix) {
        return redisTemplate.keys(prefix);
    }

    // ============== list =====================

    public void lpush(String key, Object values, long expire) {
        listOperations.leftPush(key, toJson(values));
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void lpush(String key, Object values) {
        lpush(key, values, DEFAULT_EXPIRE);
    }

    public void lpushAll(String key, List values, long expire) {
        listOperations.leftPushAll(key, toJson(values));
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void lpushAll(String key, List values) {
        lpushAll(key, values, DEFAULT_EXPIRE);
    }

    public <T> List<T> lrange(String key, long var1, long var2, Class<T> clazz) {
        List<Object> range = listOperations.range(key, var1, var2);
        List<T> result = new ArrayList<T>();
        for (Object o : range) {
            result.add(fromJson((String) o, clazz));
        }
        return result;
    }

    public <T> List<T> lrange(String key, long var, Class<T> clazz) {
        return lrange(key, 0, var, clazz);
    }

    public void lremove(String key, long count, Object o) {
        listOperations.remove(key, count, o);
    }

    public void lremove(String key, long count) {
        lremove(key, count, "");
    }

    public void lremove(String key) {
        lremove(key, 0, "");
    }

    public long lsize(String key) {
        return listOperations.size(key);
    }

    // ============== set =====================

    public void sadd(String key, Object value, long expire) {

        setOperations.add(key, toJson(value));
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void sadd(String key, Object value) {
        sadd(key, value, DEFAULT_EXPIRE);
    }

    public <T> Set<T> sget(String key, Class<T> clazz) {
        Set<Object> members = setOperations.members(key);
        Set<T> result = new HashSet<>();

        for (Object member : members) {
            result.add(fromJson((String) member, clazz));
        }

        return result;
    }

    // ============== hash =====================
    public void hputAll(String key, Map<String, Object> values, long expire) {
        Map<String, Object> map = new HashMap<>();
        values.forEach((vKey, vValue) -> {
            map.put(vKey, toJson(vValue));
        });
        hashOperations.putAll(key, map);
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void hputAll(String key, Map<String, Object> values) {
        hputAll(key, values, DEFAULT_EXPIRE);
    }

    public void hput(String key, String hashKey, Object value, long expire) {
        Map<String, Object> map = new HashMap<>();
        map.put(hashKey, value);
        hputAll(key, map, expire);
    }

    public void hput(String key, String hashKey, Object value) {
        hput(key, hashKey, value, DEFAULT_EXPIRE);
    }

    public <T> T hget(String key, String hashKey, Class<T> clazz) {
        return fromJson((String) hashOperations.get(key, hashKey), clazz);
    }

    public long hsize(String key) {
        return hashOperations.size(key);
    }

    public <T> Map<String, T> hentries(String key, Class<T> clazz) {
        Map<String, Object> map = hashOperations.entries(key);
        HashMap<String, T> result = new HashMap<>();

        map.forEach((vKey, vValue) -> {
            result.put(vKey, fromJson((String) vValue, clazz));
        });

        return result;
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

}
