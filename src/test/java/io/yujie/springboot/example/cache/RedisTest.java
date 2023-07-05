package io.yujie.springboot.example.cache;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.yujie.springboot.example.entity.dto.GeoPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;

import static io.yujie.springboot.example.EnvHelper.getEnv;

@Configuration
public class RedisTest {


    @Test
    public void test() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setEnvironment(getEnv());
        context.register(RedisAutoConfiguration.class);
        context.refresh();

        StringRedisTemplate bean = context.getBean(StringRedisTemplate.class);
        bean.opsForValue().set("china", "中国", Duration.ofSeconds(60));
        String china = bean.opsForValue().get("china");
        System.out.println("china = " + china);
        context.close();
    }


    @Autowired
    public void custom(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer();

        //通过这里可以使得字段长度不一致的实体可以正常转化
        Field mapper = ReflectionUtils.findField(redisSerializer.getClass(), "mapper");
        mapper.setAccessible(true);
        ObjectMapper field =(ObjectMapper) ReflectionUtils.getField(mapper, redisSerializer);
        field.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        redisTemplate.setDefaultSerializer(redisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(redisSerializer);
    }

    @Test
    public void test2() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setEnvironment(getEnv());
        context.register(RedisAutoConfiguration.class, this.getClass());
        context.refresh();

        RedisTemplate<String,Object> bean = context.getBean("redisTemplate",RedisTemplate.class);
//
//        GeoPoint geoPoint = new GeoPoint();
//        geoPoint.setLatitude(114.5678);
//        geoPoint.setLongitude(24.3456);
//        bean.opsForValue().set("geo", geoPoint);

        Object geo = bean.opsForValue().get("geo");
        System.out.println("geo = " + geo);
        context.close();
    }

}
