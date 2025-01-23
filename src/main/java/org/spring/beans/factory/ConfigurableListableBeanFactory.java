package org.spring.beans.factory;

import org.spring.beans.BeansException;
import org.spring.beans.factory.config.AutowireCapableBeanFactory;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {


    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
