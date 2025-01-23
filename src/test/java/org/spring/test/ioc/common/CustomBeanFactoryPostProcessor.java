package org.spring.test.ioc.common;

import org.spring.beans.BeansException;
import org.spring.beans.PropertyValue;
import org.spring.beans.PropertyValues;
import org.spring.beans.factory.ConfigurableListableBeanFactory;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * 在beanDefinition加载完成之后，bean实例化之前，提供修改beanDefinition属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "ZhangXiaoshi"));
    }
}
