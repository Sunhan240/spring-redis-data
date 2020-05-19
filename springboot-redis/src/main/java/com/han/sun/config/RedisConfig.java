/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/15 5:41 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/5/15 5:41 PM
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
