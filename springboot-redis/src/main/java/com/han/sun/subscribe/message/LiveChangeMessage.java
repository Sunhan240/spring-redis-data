/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 11:06
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.subscribe.message;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 11:06
 */
@Getter
@Setter
@NoArgsConstructor
public class LiveChangeMessage extends BaseSubMessage {


    /**
     * 直播id
     */
    private String liveIds;

    public LiveChangeMessage(String json) {
        super(json);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
