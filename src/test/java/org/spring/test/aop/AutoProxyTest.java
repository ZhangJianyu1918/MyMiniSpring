package org.spring.test.aop;

import org.junit.Test;
import org.spring.context.support.ClassPathXmlApplicationContext;
import org.spring.test.service.WorldService;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class AutoProxyTest {

    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        //获取代理对象
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
    }
}
