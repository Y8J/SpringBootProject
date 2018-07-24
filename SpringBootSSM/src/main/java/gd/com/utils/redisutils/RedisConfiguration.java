package gd.com.utils.redisutils;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 1、通过继承CachingConfigurerSupport，将Spring-Cache的缓存换为Redis 
 * 2、使用适合项目的key生成策略cacheKeyGenerator
 * @author yangjing
 *
 */
/*@Configuration
@EnableCaching*/
public class RedisConfiguration extends CachingConfigurerSupport {
	private final static String redis_key_questions="ACTIVITY_QUESTION";
    private final static String redis_key_annual="ACTIVITY_ANNUAL";
    private final static String redis_key_action="ACTIVITY_ACTION";
 
 
    @Bean
    public KeyGenerator ACTIVITY_QUESTION_KEY(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
 
                return redis_key_questions;
            }
        };
 
    }
 
    @Bean
    public KeyGenerator ACTIVITY_ACTION_KEY(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
 
                return redis_key_action;
            }
        };
 
    }
 
    @Bean
    public KeyGenerator ACTIVITY_ANNUAL_KEY(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
 
                return redis_key_annual;
            }
        };
 
    }
 
    @Bean
    public KeyGenerator KeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
 
    }
 
    @Bean
    public CacheManager cacheManager(
            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
 
    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
