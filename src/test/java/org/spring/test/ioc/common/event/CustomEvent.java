package org.spring.test.ioc.common.event;

import org.spring.context.ApplicationContext;
import org.spring.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
