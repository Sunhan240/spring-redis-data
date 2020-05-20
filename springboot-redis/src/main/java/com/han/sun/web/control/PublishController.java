/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 11:44
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.web.control;

import com.han.sun.publish.RedisChannelEnums;
import com.han.sun.publish.RedisPublish;
import com.han.sun.publish.message.LiveChangeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br>
 * TODO(redis消息发布控制器)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 11:44
 */
@Controller
public class PublishController {

    private static final Logger log = LogManager.getLogger(PublishController.class);

    private final RedisPublish redisPublish;

    @Autowired
    public PublishController(RedisPublish redisPublish) {
        this.redisPublish = redisPublish;
    }

    @ResponseBody
    @RequestMapping(value = "publish")
    public String test1(String json) {
        LiveChangeMessage liveChangeMessage = new LiveChangeMessage();
        liveChangeMessage.setLiveIds("sunhan");
        liveChangeMessage.setExtra("这是一个孙寒的发布消息");
        redisPublish.sendMessage(RedisChannelEnums.LIVE_INFO_CHANGE, liveChangeMessage);
        return json;
    }


}
