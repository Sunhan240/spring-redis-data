/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/22 3:25 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/5/22 3:25 PM
 */
public class ObjectRedisSerializer implements RedisSerializer<Object> {


    private static final Logger log = LogManager.getLogger(ObjectRedisSerializer.class);

    /**
     * 定义序列化和反序列化转化类
     */
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    /**
     * 定义转换空字节数组
     */
    private static final byte[] EMPTY_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        byte[] byteArray;
        if (null == obj) {
            log.info("----------------->:Redis待序列化的对象为空.");
            byteArray = EMPTY_ARRAY;
        } else {
            try {
                byteArray = serializer.convert(obj);
            } catch (Exception e) {
                log.error("----------------->Redis序列化对象失败,异常：" + e.getMessage(), e);
                byteArray = EMPTY_ARRAY;
            }
        }
        return byteArray;
    }

    @Override
    public Object deserialize(byte[] datas) throws SerializationException {
        Object obj = null;
        if ((null == datas) || (datas.length == 0)) {
            log.info("----------------->:Redis待反序列化的对象为空.");
            return null;
        } else {
            try {
                obj = deserializer.convert(datas);
            } catch (Exception e) {
                log.error("----------------->Redis序列化对象失败,异常：" + e.getMessage(), e);
            }
        }
        return obj;
    }
}