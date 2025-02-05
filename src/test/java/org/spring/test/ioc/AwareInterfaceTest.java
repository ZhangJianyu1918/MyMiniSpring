package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.context.support.ClassPathXmlApplicationContext;
import org.spring.test.service.HelloService;

import static org.assertj.core.api.Assertions.assertThat;


public class AwareInterfaceTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        helloService.sayHello();
        assertThat(helloService.getApplicationContext()).isNotNull();
        assertThat(helloService.getBeanFactory()).isNotNull();


    }
}
