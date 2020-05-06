package com.eastrobot.robotdev;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * 〈function〉<br>
 * TODO(燃气认证信息接口)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2019/9/23 11:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring*.xml"})
public class BaseTest extends TestCase {



}
