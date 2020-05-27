/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/20 15:24
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.json;

import com.google.gson.Gson;
import com.han.sun.subscribe.message.LiveChangeMessage;
import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/20 15:24
 */
public class GsonTest {

    @Test
    public void test1() {
        Gson gson = new Gson();
        LiveChangeMessage message = new LiveChangeMessage();
        message.setLiveIds("sunhan");
        message.setChannel("weixin");
        message.setExtra("这是孙寒的一个测试用例！");
        String json = gson.toJson(message);
        System.out.println(json);
    }

    @Test
    public void test2() {
        Gson gson = new Gson();
        String s = "{\"liveIds\":\"sunhan\",\"channel\":\"weixin\",\"extra\":\"这是孙寒的一个测试用例！\"}";
        LiveChangeMessage message = gson.fromJson(s, LiveChangeMessage.class);
        System.out.println(message.toString());
    }

}
