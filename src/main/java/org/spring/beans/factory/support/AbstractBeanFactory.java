package org.spring.beans.factory.support;

import org.spring.beans.BeansException;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.config.BeanPostProcessor;
import org.spring.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory
        extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return ((T) getBean(name));
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 覆盖
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }
}
