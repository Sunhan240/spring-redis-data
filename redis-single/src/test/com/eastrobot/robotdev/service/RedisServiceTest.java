package com.eastrobot.robotdev.service;


import com.alibaba.fastjson.JSON;
import com.eastrobot.robotdev.BaseTest;
import com.eastrobot.robotdev.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisServiceTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(RedisServiceTest.class);

    @Autowired
    private RedisService redisService;


    @Test
    public void getValue() {
        Object name = redisService.get("name");
        log.info("name:{}", name);
        Object password = redisService.get("password");
        log.info("name:{}", password);
    }


    /**
     * 备注，redisTemplate不可以直接操作对象实体，
     * 解决方案：
     * 1、hash类型
     * 2、转json存储、与反序列化
     */
    @Test
    public void testObject() {

        // 使用JSON格式存储、获取
        User user = new User("孙寒", "sunhan240", 24);
        if (redisService.set("sunhan", user.toString())) {
            log.info("存储user成功！");
        }
        Object o = redisService.get("sunhan");
        User user1 = JSON.parseObject(o.toString(), User.class);
        log.info("user:{}", user1.toString());

        // hash类型
        redisService.hset("sunhan1", "username", user.getUsername());
        redisService.hset("sunhan1", "password", user.getPassword());
        redisService.hset("sunhan1", "age", user.getAge()+"");

        Object username = redisService.hget("sunhan1", "username");
        Object password = redisService.hget("sunhan1", "password");
        Object age = redisService.hget("sunhan1", "age");
        log.info("username:{} | password:{} | age:{}", username, password, age);
    }


    @Test
    public void testList(){

    }


}