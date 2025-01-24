package org.spring.beans.factory.config;

import org.spring.beans.BeansException;
import org.spring.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    /**
     * 在beanDefinition加载完成之后，bean实例化之前，提供修改beanDefinition属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
