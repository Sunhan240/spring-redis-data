/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/22 10:45 AM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.han.sun.queue.list;

import com.han.sun.entity.DeveloperBook;
import com.han.sun.util.ThreadPoolUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * TODO(使用redis的list类型来做mq方案)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/5/22 10:45 AM
 */
@Service
public class ConsumerForList {

    private static final Logger log = LogManager.getLogger(ConsumerForList.class);

    private ListOperations<Serializable, Object> opsForList;

    @Autowired
    public ConsumerForList(RedisTemplate< Serializable, Object> redisTemplate) {
        opsForList = redisTemplate.opsForList();

        // 初始化的时候调用消费者
        // consume1();
        // consumeForBrPop();
    }


    /**
     * 【从redis中list进行弹栈。】
     *
     * @since 2020/5/22 10:59 AM sunhan
     */
    private void consume1() {
        String key = "book";
        ThreadPoolUtils.execute(() -> {
            while (opsForList.size(key) != null) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }

                Object o = opsForList.rightPop("book");
                if (o instanceof DeveloperBook) {
                    log.info("弹栈:{}", o.toString());
                }
            }
            log.info("************Book queue中已无数据!***************");
        });
    }


    /**
     * 【使用brpop指令，这个指令只有在有元素时才返回，没有则会阻塞直到超时返回null】
     * 该方式可以返回list中多个记录值
     * @since 2020/5/22 5:00 PM sunhan
     */
    private void consumeForBrPop() {
        String key = "book1";
        ThreadPoolUtils.execute(() -> {
            while (opsForList.size(key) != null) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }

                Object o = opsForList.rightPopAndLeftPush("book1","book2",4000,TimeUnit.MILLISECONDS);
                log.info(o);
                if (o instanceof DeveloperBook) {
                    log.info("弹栈:{}", o.toString());
                }
            }
            log.info("************Book queue中已无数据!***************");
        });
    }

}

