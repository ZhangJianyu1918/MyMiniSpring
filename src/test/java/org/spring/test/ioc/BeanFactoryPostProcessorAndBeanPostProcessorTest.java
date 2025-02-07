package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.beans.factory.support.DefaultListableBeanFactory;
import org.spring.beans.factory.xml.XmlBeanDefinitionReader;
import org.spring.test.bean.Car;
import org.spring.test.bean.Person;
import org.spring.test.common.CustomBeanFactoryPostProcessor;
import org.spring.test.common.CustomBeanPostProcessor;

import static org.assertj.core.api.Assertions.assertThat;


public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

    @Test
    public void testBeanFactoryPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 此时beanDefinition已加载完成，但bean还没实例化，这时可以修改已生成的beanDefinition的属性
        CustomBeanFactoryPostProcessor customBeanPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("ZhangXiaoshi");
    }

    @Test
    public void testBeanPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //
        CustomBeanPostProcessor customBeanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customBeanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        assertThat(car.getBrand()).isEqualTo("BYD");
    }
}
