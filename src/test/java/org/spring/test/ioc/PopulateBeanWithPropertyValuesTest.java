package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.beans.BeansException;
import org.spring.beans.PropertyValue;
import org.spring.beans.PropertyValues;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.config.BeanReference;
import org.spring.beans.factory.support.DefaultListableBeanFactory;
import org.spring.test.bean.Car;
import org.spring.test.bean.Person;

public class PopulateBeanWithPropertyValuesTest {

    @Test
    public void testPopulateBeanWithPropertyValues() throws BeansException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册Car实例
        PropertyValues propertyValuesForCar = new PropertyValues();
        propertyValuesForCar.addPropertyValue(new PropertyValue("brand", "xiaomi"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, propertyValuesForCar);
        beanFactory.registerBeanDefinition("car", carBeanDefinition);

        // 注册Person实例
        PropertyValues propertyValuesForPerson = new PropertyValues();
        propertyValuesForPerson.addPropertyValue(new PropertyValue("name", "zjy"));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("age", 18));
        // Person实例依赖Car实例
        propertyValuesForPerson.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValuesForPerson);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }
}
