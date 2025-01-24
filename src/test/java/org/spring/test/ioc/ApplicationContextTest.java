package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.beans.context.support.ClassPathXmlApplicationContext;
import org.spring.test.ioc.bean.Car;
import org.spring.test.ioc.bean.Person;

public class ApplicationContextTest {

    @Test
    public void testApplicationContext() throws Exception {
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
    }
}
