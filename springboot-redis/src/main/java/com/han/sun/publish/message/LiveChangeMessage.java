/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 10:34
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.publish.message;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * TODO(消息对象)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 10:34
 */
@Getter
@Setter
@NoArgsConstructor
public class LiveChangeMessage extends BasePubMessage {


    private String liveIds;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
