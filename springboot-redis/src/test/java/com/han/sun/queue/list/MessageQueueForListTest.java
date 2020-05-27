package com.han.sun.queue.list;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageQueueForListTest {



    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //@Test
    public void consume(){

        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ListOperations<String, String> opsForList = redisTemplate.opsForList();
            String bookJson = opsForList.rightPop("book");
            System.out.println(bookJson);
        }

    }


}