package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.context.support.ClassPathXmlApplicationContext;
import org.spring.test.bean.Car;

public class ValueAnnotationTest {

    @Test
    public void testValueAnnotation() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car.getBrand());
    }
}
