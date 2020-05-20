/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 11:08
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.subscribe.impl;

import com.han.sun.subscribe.RedisSubscribe;
import com.han.sun.subscribe.message.LiveChangeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 11:08
 */
@Service
public class LiveChangeSubscribeImpl implements RedisSubscribe {


    private static final Logger log = LogManager.getLogger(LiveChangeSubscribeImpl.class);

    @Override
    public void receiveMessage(String jsonMessage) {
        log.info("接受订阅消息！------------{}", jsonMessage);
        LiveChangeMessage liveChangeMessage = new LiveChangeMessage(jsonMessage);
        log.info("直播消息:{}", liveChangeMessage.toString());
    }
}
