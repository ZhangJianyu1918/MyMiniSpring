package org.spring.context.event;

import org.spring.beans.BeansException;
import org.spring.beans.factory.BeanFactory;
import org.spring.beans.factory.BeanFactoryAware;
import org.spring.context.ApplicationEvent;
import org.spring.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster
        implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>>
            applicationListenerSet = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListenerSet.add(
                (ApplicationListener<ApplicationEvent>) listener
        );
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListenerSet.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
