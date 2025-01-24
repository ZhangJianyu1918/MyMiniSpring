package org.spring.beans.factory;

import org.spring.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{

    /**
     * 返回指定类型的所有bean
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回定义的所有bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
