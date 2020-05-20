/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 10:39
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.publish.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.sun.publish.RedisChannelEnums;
import com.han.sun.publish.RedisPublish;
import com.han.sun.publish.message.BasePubMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 10:39
 */
@Service
public class RedisPublishImpl implements RedisPublish {


    private static final Logger log = LogManager.getLogger(RedisPublishImpl.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendMessage(RedisChannelEnums redisChannelEnums, BasePubMessage basePubMessage) {
        if (redisChannelEnums == null || basePubMessage == null) {
            return;
        }
        basePubMessage.setChannel(redisChannelEnums.getCode());
        stringRedisTemplate.convertAndSend(redisChannelEnums.getCode(), basePubMessage.toString());
        log.info("消息发布成功！");
    }
}
