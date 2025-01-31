package org.spring.context.event;

import org.spring.context.ApplicationContext;
import org.spring.context.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
