package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.beans.factory.support.DefaultListableBeanFactory;
import org.spring.beans.factory.xml.XmlBeanDefinitionReader;
import org.spring.test.ioc.bean.Car;
import org.spring.test.ioc.bean.Person;

public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
