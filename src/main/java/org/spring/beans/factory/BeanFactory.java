package org.spring.beans.factory;

import org.spring.beans.BeansException;

public interface BeanFactory {

    public Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);
}
