package org.spring.beans.factory.support;

import org.spring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * 把spring认定为component的类注册进来
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
