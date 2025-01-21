package org.spring.beans.factory.support;

import org.spring.beans.BeansException;
import org.spring.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
