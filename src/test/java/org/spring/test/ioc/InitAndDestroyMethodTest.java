package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.context.support.ClassPathXmlApplicationContext;

public class InitAndDestroyMethodTest {

    @Test
    public void testInitAndDestroyContext() throws Exception {
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext(
                        "classpath:init-and-destroy-method.xml");

        applicationContext.registerShutdownHook();
    }
}
