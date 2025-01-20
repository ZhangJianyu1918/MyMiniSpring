package org.spring.beans.factory;

import org.spring.beans.BeansException;

public interface BeanFactory {

    public Object getBean(String name) throws BeansException;

}
