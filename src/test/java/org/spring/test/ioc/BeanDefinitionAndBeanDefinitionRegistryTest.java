package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.support.DefaultListableBeanFactory;
import org.spring.test.ioc.service.HelloService;

public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    public void testBeanFactory() throws Exception {

        // 模拟生成bean的过程
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        defaultListableBeanFactory.registerBeanDefinition("helloService", beanDefinition);

        // 模拟取出bean的过程
        HelloService helloService = (HelloService) defaultListableBeanFactory.getBean("helloService");
        helloService.test();
    }
}
