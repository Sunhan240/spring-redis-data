package com.eastrobot.robotdev;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 〈function〉<br>
 * TODO(controller层测试单元)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2019/8/30 11:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring*.xml"})
@WebAppConfiguration
public class ControllerBaseTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()
    public void setup() {
        //初始化MockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void test() throws Exception {
        String responseStr = mockMvc.perform(post("/test.do")
                .param("name", "test").param("date", "2018-05-04"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseStr);
    }

    @Test
    public void test1() throws Exception {
        String responseStr = mockMvc.perform(post("/test1.do")
                .param("name", "1")).andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(responseStr);
    }
}
