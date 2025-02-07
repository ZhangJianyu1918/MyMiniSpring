package org.spring.beans.factory.config;

import org.spring.beans.BeansException;
import org.spring.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行BeanPostProcessor的postProcessBeforeInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessor的postProcessAfterInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
