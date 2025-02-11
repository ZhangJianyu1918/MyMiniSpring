package org.spring.beans.factory.config;

import org.spring.beans.BeansException;

/**
 * 修改实例化之后的bean
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
