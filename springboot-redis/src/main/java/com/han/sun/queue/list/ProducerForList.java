/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/5/21 5:36 PM
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * TODO(使用redis的list类型来做mq方案)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/5/21 5:36 PM
 */
@Service
public class ProducerForList {


    private static final Logger log = LogManager.getLogger(ProducerForList.class);


    private ListOperations<Serializable, Object> opsForList;

    @Autowired
    public ProducerForList(RedisTemplate<Serializable, Object> redisTemplate) {
        opsForList = redisTemplate.opsForList();
        // 初始化的时候调用生产者
        // productForOne();
        // productForList();
    }


    /**
     * 【生产一条数据放入redis的list中】
     *
     * @since 2020/5/22 09:30 AM sunhan
     */
    private void productForOne() {
        ThreadPoolUtils.execute(() -> {
            long size = 0;
            int temp = 10000;
            while (size < temp) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                DeveloperBook book = new DeveloperBook();
                book.setPage(new Random().nextInt(300) + "");
                book.setArticleId(new Random().nextFloat() + "");
                book.setAbstractContent(UUID.randomUUID().toString());
                book.setCategory(UUID.randomUUID().toString());

                Long aLong = opsForList.leftPush("book", book);
                if (aLong != null) {
                    size = aLong;
                }
                log.info("Book Queue----》{}条数据", aLong);
            }
        });
    }


    /**
     * 【生产者一次性将集合放入redis的list中】
     *
     * @since 2020/5/22 09:30 AM sunhan
     */
    private void productForList() {
        ThreadPoolUtils.execute(() -> {
            long size = 0;
            int temp = 10000;
            while (size < temp) {
                try {
                    TimeUnit.MILLISECONDS.sleep(6000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                List<DeveloperBook> list = new ArrayList<>();

                for (int i = 0; i < 20; i++) {
                    DeveloperBook book = new DeveloperBook();
                    book.setPage(new Random().nextInt(300) + "");
                    book.setArticleId(new Random().nextFloat() + "");
                    book.setAbstractContent(UUID.randomUUID().toString());
                    book.setCategory(UUID.randomUUID().toString());
                    list.add(book);
                }
                Long aLong = opsForList.leftPushAll("book1", list);
                if (aLong != null) {
                    size = aLong;
                }
                log.info("Book Queue----》{}条数据", aLong);
            }
        });
    }


}
