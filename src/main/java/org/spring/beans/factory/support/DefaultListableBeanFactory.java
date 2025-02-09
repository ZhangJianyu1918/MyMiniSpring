package org.spring.beans.factory.support;

import org.spring.beans.BeansException;
import org.spring.beans.factory.ConfigurableListableBeanFactory;
import org.spring.beans.factory.config.BeanDefinition;

import java.util.*;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined.");
        }
        return beanDefinition;
    }

    /**
     * 提前实例化所有单例实例
     *
     * @throws BeansException
     */
    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
        /*beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            if (beanDefinition.isSingleton()){
                getBean(beanName);
            }
        });*/
    }

    /**
     * 判断是否包含指定的BeanDefinition
     *
     * @param beanName
     * @return
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 返回指定类型的所有bean
     *
     * @param type
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        HashMap<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                T bean = (T) getBean(beanName);
                result.put(beanName, bean);
            }
        });
        return result;
    }

    /**
     * 返回定义的所有bean的名称
     *
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> keySet = beanDefinitionMap.keySet();
        return keySet.toArray(new String[0]);
    }

    /**
     * 把spring认定为component的类注册进来
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }
        if (beanNames.size() == 1) {
            return getBean(beanNames.get(0), requiredType);
        }
        throw new BeansException(requiredType + "expected single bean but found" +
                beanNames.size() + ":" + beanNames);
    }

 }
