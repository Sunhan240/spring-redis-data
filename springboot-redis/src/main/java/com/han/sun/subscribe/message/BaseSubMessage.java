/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 11:04
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.subscribe.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 11:04
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseSubMessage {


    /**
     * 发布定于频道名称
     */
    private String channel;

    private String extra;

    private String json;


    public BaseSubMessage(String json) {
        this.json = json;
    }
}
