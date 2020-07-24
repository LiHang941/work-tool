package com.github.lihang941.tool.redis.template;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author : lihang1329@gmail.com
 * @since : 2018/9/3
 */
public abstract class JsonRedisTemplate<T> extends RedisTemplate<String, T> {

    private Class<T> clazz;

    private FastJsonRedisSerializer<T> fastJsonRedisSerializer;

    public JsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) type; // JsonRedisTemplate<Employee>
        Type[] types = pt.getActualTypeArguments();
        this.clazz = (Class<T>) types[0];
        setConnectionFactory(redisConnectionFactory);
        this.fastJsonRedisSerializer = new FastJsonRedisSerializer<>(clazz);
        setValueSerializer(fastJsonRedisSerializer);
        setHashValueSerializer(fastJsonRedisSerializer);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        setKeySerializer(stringRedisSerializer);
        setHashKeySerializer(stringRedisSerializer);
        afterPropertiesSet();
    }

    public JsonRedisTemplate(RedisConnectionFactory redisConnectionFactory, Class<T> clazz) {
        setConnectionFactory(redisConnectionFactory);
        this.fastJsonRedisSerializer = new FastJsonRedisSerializer<T>(clazz);
        setValueSerializer(fastJsonRedisSerializer);
        setHashValueSerializer(fastJsonRedisSerializer);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        setKeySerializer(stringRedisSerializer);
        setHashKeySerializer(stringRedisSerializer);
        afterPropertiesSet();
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public FastJsonRedisSerializer<T> getFastJsonRedisSerializer() {
        return fastJsonRedisSerializer;
    }
}
