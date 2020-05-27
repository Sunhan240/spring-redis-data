/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/15 5:41 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.config;

import com.han.sun.subscribe.RedisChannelEnums;
import com.han.sun.subscribe.RedisSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    /**
     * 【配置redis的key、value可序列化类型】
     *
     * @param redisConnectionFactory RedisConnectionFactory(redis连接池)
     * @return RedisTemplate
     * @since 2020/5/20 14:47 han.sun
     */
    @Bean
    public RedisTemplate<String, Object> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RedisTemplate<Serializable, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Serializable, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        // redis存取对象的关键配置
        template.setKeySerializer(new StringRedisSerializer());
        // ObjectRedisSerializer类为java对象的序列化和反序列化工具类
        template.setValueSerializer(new ObjectRedisSerializer());
        return template;
    }

    /**
     * 存放策略实例
     * key---beanName
     * value---对应的策略实现
     */
    private ConcurrentHashMap<String, RedisSubscribe> instanceMap = new ConcurrentHashMap<>(16);

    @Autowired
    public RedisConfig(Map<String, RedisSubscribe> strategyMap) {
        instanceMap.clear();
        strategyMap.forEach((k, v) -> instanceMap.put(k.toLowerCase(), v));
    }

    /**
     * 【Redis消息订阅服务---监听器容器】
     *
     * @param connectionFactory 连接池工程构建
     * @return 监听容器
     * @since 2020/5/20 11:30 han.sun
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        RedisChannelEnums[] redisChannelEnums = RedisChannelEnums.values();
        if (redisChannelEnums.length > 0) {
            for (RedisChannelEnums redisChannelEnum : redisChannelEnums) {
                if (redisChannelEnum == null || redisChannelEnum.getCode() == null || redisChannelEnum.getClassName() == null) {
                    continue;
                }
                // 订阅一个叫pmp和channel的通道，多通道
                // 一个订阅者接受一个频道信息，新增订阅者需要新增RedisChannelEnum定义+RedisSubscribe的实现
                String toLowerCase = redisChannelEnum.getClassName().getSimpleName().toLowerCase();
                RedisSubscribe redisSubscribe = instanceMap.get(toLowerCase);
                container.addMessageListener(listenerAdapter(redisSubscribe), new PatternTopic(redisChannelEnum.getCode()));
            }
        }
        return container;
    }


    /**
     * 【配置消息订阅服务处理类】
     *
     * @param redisSubscribe 自定义消息订阅---接口
     * @return 消息监听适配器
     * @since 2020/5/20 11:31 han.sun
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisSubscribe redisSubscribe) {
        // 这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        // 也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        // 注意2个通道调用的方法都要为receiveMessage
        return new MessageListenerAdapter(redisSubscribe, "receiveMessage");
    }

}
