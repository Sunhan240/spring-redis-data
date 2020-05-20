/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 15:14
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.sun.subscribe.message.LiveChangeMessage;
import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 15:14
 */
public class JacksonTest {


    @Test
    public void test1() {
        ObjectMapper mapper = new ObjectMapper();

        LiveChangeMessage message = new LiveChangeMessage();
        message.setLiveIds("sunhan");
        message.setChannel("weixin");
        message.setExtra("这是孙寒的一个测试用例！");
        try {
            String value = mapper.writer().writeValueAsString(message);
            System.out.println(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
