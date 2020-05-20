/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 10:37
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.publish;

import com.han.sun.publish.message.BasePubMessage;

/**
 * 〈一句话功能简述〉<br>
 * TODO(发布者服务接口)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 10:37
 */
public interface RedisPublish {


    /**
     * 【 继承redis实现消息发布订阅模式---双通道】
     *
     * @param redisChannelEnums 枚举定义
     * @param basePubMessage    消息
     * @since 2020/5/20 10:38 han.sun
     */
    void sendMessage(RedisChannelEnums redisChannelEnums, BasePubMessage basePubMessage);

}
