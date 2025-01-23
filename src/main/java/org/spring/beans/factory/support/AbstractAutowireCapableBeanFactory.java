package org.spring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import org.spring.beans.BeansException;
import org.spring.beans.PropertyValue;
import org.spring.beans.factory.config.AutowireCapableBeanFactory;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.config.BeanPostProcessor;
import org.spring.beans.factory.config.BeanReference;

public abstract class AbstractAutowireCapableBeanFactory
        extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
            // 给bean填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行bean的初始化方法和BeanPostProcessor的前置和后置过程
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行BeanPostProcessor的前置处理
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        // TODO 后面再实现bean的初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 执行BeanPostProcessor的后置处理
        wrappedBean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : this.getBeanPostProcessorList()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        System.out.println("执行bean [" + beanName + "] 的初始化方法");
    }

    private Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException{
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessorList()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // beanA依赖beanB，则先去初始化beanB
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                // 通过反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("Error setting property values for bean: " + beanName, e);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
