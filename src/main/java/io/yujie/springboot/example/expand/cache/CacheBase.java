package io.yujie.springboot.example.expand.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * K:V 格式
 * @param <T>
 */
@Slf4j
public class CacheBase<T> {

    protected static final String EMPTY_OBJ = "{}";

    @Autowired
    private StringRedisTemplate redisTemplate;

    protected TimeUnit timeUnit = TimeUnit.SECONDS;

    //失效时间为: 默认2h
    protected int expire = 60 * 60 * 2;

    //上下浮动时间 : 默认10min
    protected int upAndDown = 10 * 60;


    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void clear(String key) {
        redisTemplate.delete(key);
    }

    public void clear(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    protected Duration getExpireTime() {
        int nextInt = RandomUtils.nextInt(0, upAndDown);
        return Duration.ofSeconds(expire - nextInt);
    }
}
