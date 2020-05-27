/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/25 12:20 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.web.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * TODO(测试控制器)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/5/25 12:20 PM
 */
@RestController
public class TestController {

    private static final Logger log = LogManager.getLogger(TestController.class);


    private final RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public TestController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(value = "jedis")
    public String test2() {
        log.info("redis连接工厂:{}", redisTemplate.getConnectionFactory());

        //添加key
        redisTemplate.opsForValue().set("user", "张三");
        //获取key
        Object user = redisTemplate.opsForValue().get("user");
        log.info("从redis中获取key=user的值为：{}", user);

        //删除key
        redisTemplate.delete("user");
        return user.toString();
    }

}
