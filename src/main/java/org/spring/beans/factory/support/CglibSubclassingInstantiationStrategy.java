package org.spring.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.spring.beans.BeansException;
import org.spring.beans.factory.config.BeanDefinition;


public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    /**
     * 使用CGLIB动态生成子类
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Enhancer enhancer = new Enhancer(); // Enhancer 是 CGLIB 的核心类，用于动态创建类的子类。
        enhancer.setSuperclass(beanDefinition.getBeanClass()); // 通过 setSuperclass 方法指定要生成子类的父类，即目标 Bean 的 Class。
        enhancer.setCallback(
                (MethodInterceptor) (obj, method, argsTemp, proxy)
                        -> proxy.invokeSuper(obj, argsTemp)
        );
        return enhancer.create();
    }
}
