package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.context.support.ClassPathXmlApplicationContext;
import org.spring.test.common.event.CustomEvent;

public class EventAndEventListerTest {

    @Test
    public void testEventListener() throws Exception {
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));

        applicationContext.registerShutdownHook();

    }
}
