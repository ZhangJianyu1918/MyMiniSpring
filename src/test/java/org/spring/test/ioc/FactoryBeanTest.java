package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.context.support.ClassPathXmlApplicationContext;
import org.spring.test.bean.Car;
import static org.assertj.core.api.Assertions.assertThat;

public class FactoryBeanTest {

    @Test
    public void testFactoryBean() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

        Car car = applicationContext.getBean("car", Car.class);
        applicationContext.getBean("car");
        assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
