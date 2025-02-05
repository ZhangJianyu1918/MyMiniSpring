package org.spring.test.common;

import org.spring.beans.BeansException;
import org.spring.beans.factory.config.BeanPostProcessor;
import org.spring.test.bean.Car;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization");
        if (beanName.equals("car")) {
            ((Car) bean).setBrand("BYD");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
        return bean;
    }
}
