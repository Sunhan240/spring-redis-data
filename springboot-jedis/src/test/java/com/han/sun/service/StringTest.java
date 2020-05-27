/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/15 5:34 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/5/15 5:34 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {

    @Autowired
    private RedisTemplate<String,String> template;


    @Test
    public void test1(){
        ValueOperations<String, String> opsForValue = template.opsForValue();
        opsForValue.set("sunhan","han.sun@xiaoi.com");
        String name2 = opsForValue.get("name2");
        System.out.println(name2);
        String sunhan = opsForValue.get("sunhan");
        System.out.println(sunhan);
    }

}
