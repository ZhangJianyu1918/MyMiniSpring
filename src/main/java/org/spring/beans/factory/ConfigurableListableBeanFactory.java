package org.spring.beans.factory;

import org.spring.beans.BeansException;
import org.spring.beans.factory.config.AutowireCapableBeanFactory;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.config.BeanPostProcessor;
import org.spring.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {


    /**
     * 根据名称查找bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;


    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


}
