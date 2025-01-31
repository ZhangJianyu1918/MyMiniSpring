package org.spring.context.event;

import org.spring.beans.BeansException;
import org.spring.beans.factory.BeanFactory;
import org.spring.context.ApplicationEvent;
import org.spring.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleApplicationEventMulticaster
        extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener
                : applicationListenerSet) {
            if (supportsEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    /**
     * 监听器是否对该时间感兴趣
     * @param applicationListener
     * @param event
     * @return
     */
    public boolean supportsEvent(
            ApplicationListener<ApplicationEvent> applicationListener,
            ApplicationEvent event) {
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        Type actualTypeArguments =
                ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArguments.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("Wrong event class name: " + className, e);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
