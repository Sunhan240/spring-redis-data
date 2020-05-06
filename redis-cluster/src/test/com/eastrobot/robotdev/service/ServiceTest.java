/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2020/5/6 10:08
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.eastrobot.robotdev.service;

import com.eastrobot.robotdev.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2020/5/6 10:08
 */
public class ServiceTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(ServiceTest.class);


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void test1() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        // 存入key-value
        operations.set("name2", "zhangsan123");

        // 根据key取出Value
        Object name = operations.get("name2");
        System.out.println("name2:" + name);

        // 追加
        operations.append("name2", "is man");
        name = operations.get("name2");
        System.out.println("name2:" + name);

        // 获得并修改
        operations.getAndSet("name2", "xiaoijiqiren");
        name = operations.get("name2");
        System.out.println("修改后：" + name);
    }

    @Test
    public void boundValueOperations() {
        String key = "name1";
        /*
         * 先获取redis对value的操作对象,需要先设定key
         */
        BoundValueOperations<String, String> stringTemplate = redisTemplate.boundValueOps(key);

        //赋值key
        stringTemplate.set("test123424");
        //获取value
        String value = stringTemplate.get();
        System.out.println(key + "的值为:" + value);

        //从value下标,第0位开始替换原有字符串
        stringTemplate.set("test1", 0);
        String value1 = stringTemplate.get();
        System.out.println(key + "的值为:" + value1);

        //从value下标,第1位开始替换原有字符
        stringTemplate.set("test2", 1);
        String value2 = stringTemplate.get();
        System.out.println(key + "的值为:" + value2);

        //从value下标第7位进行替换,如果超过原有字符串长度,差额中间补齐并且则将原有字符串跟新的进行拼接,
        stringTemplate.set("test3", 7);
        String value3 = stringTemplate.get();
        System.out.println(key + "的值为:" + value3);

        /*
         * 设置value缓存时间 V value, long timeout, TimeUnit unit
         * 	三个字段分别对应 value,缓存时间,缓存单位,例如天,小时等,具体的,看TimeUnit源码
         */
        //设置超时时间为1天
        stringTemplate.set("testTimeout", 1, TimeUnit.DAYS);
        //获取缓存时间,单位 秒
        Long expire = stringTemplate.getExpire();
        System.out.println(key + "的缓存时间为:" + expire);
    }


    @Test
    public void testList() {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        List<String> list = new ArrayList<>();
        list.add("sunhan");
        list.add("lisi");
        list.add("xiaoming");

        ops.leftPushAll("list1", list);
        ops.rightPushAll("list2", list);

        List list1 = ops.range("list1", 0, -1);
        for (Object o : list1) {
            log.info("1字段:{}", o);
        }
        List list2 = ops.range("list2", 0, -1);
        for (Object o : list2) {
            log.info("2字段:{}", o);
        }
    }


    @Test
    public void testListPop() {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        // 获取到list的长度
        log.info("list1长度:{}", ops.size("list1"));
        log.info("list2长度:{}", ops.size("list2"));
        // 获取 列表下标的元素，如果下标越界获取的数据为null
        log.info(ops.index("list1", 1));
        // pop取栈头、栈尾
        log.info(ops.leftPop("list2"));
        log.info(ops.rightPop("list2"));

        //
        log.info(ops.remove("list2", 0, "sunhan"));

    }


}
