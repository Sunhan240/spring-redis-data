package com.eastrobot.robotdev.service;


import com.alibaba.fastjson.JSON;
import com.eastrobot.robotdev.BaseTest;
import com.eastrobot.robotdev.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisServiceTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(RedisServiceTest.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;


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

        // 直接javabean储存
        // 底层会进行检查Serializable接口，进行序列化、反序列化
        User user = new User("孙寒", "sunhan240", 24);
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("sunhan", user);


        Object sunhan = ops.get("sunhan");
        log.info(sunhan.toString());


        // 使用JSON格式存储、获取
        if (redisService.set("sunhan", user.toString())) {
            log.info("存储user成功！");
        }
        Object o = redisService.get("sunhan");
        User user1 = JSON.parseObject(o.toString(), User.class);
        log.info("user:{}", user1.toString());

        // hash类型
        redisService.hset("sunhan1", "username", user.getUsername());
        redisService.hset("sunhan1", "password", user.getPassword());
        redisService.hset("sunhan1", "age", user.getAge() + "");

        Object username = redisService.hget("sunhan1", "username");
        Object password = redisService.hget("sunhan1", "password");
        Object age = redisService.hget("sunhan1", "age");
        log.info("username:{} | password:{} | age:{}", username, password, age);
    }


    @Test
    public void testList() {

    }


}