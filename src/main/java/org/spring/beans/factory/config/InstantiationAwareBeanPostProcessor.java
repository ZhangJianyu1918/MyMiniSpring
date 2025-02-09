package org.spring.beans.factory.config;

import org.spring.beans.BeansException;
import org.spring.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 此函数在bean实例化之前执行
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;


    PropertyValues postProcessPropertyValues(
            PropertyValues propertyValues,
            Object bean,
            String beanName) throws BeansException;
}
