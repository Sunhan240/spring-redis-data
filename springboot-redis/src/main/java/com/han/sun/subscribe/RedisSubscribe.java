/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 10:51
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.subscribe;

/**
 * 〈一句话功能简述〉<br>
 * TODO(接受订阅消息的接口)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 10:51
 */
public interface RedisSubscribe {

    /**
     * 【接受订阅消息】
     *
     * @param jsonMessage 订阅消息
     * @since 2020/5/20 10:53 han.sun
     */
    void receiveMessage(String jsonMessage);

}

