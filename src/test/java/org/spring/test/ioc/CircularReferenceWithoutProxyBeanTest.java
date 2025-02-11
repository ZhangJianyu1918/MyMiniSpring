package org.spring.test.ioc;

import org.junit.jupiter.api.Test;
import org.spring.context.support.ClassPathXmlApplicationContext;
import org.spring.test.bean.A;
import org.spring.test.bean.B;

public class CircularReferenceWithoutProxyBeanTest {

    @Test
    public void testCircularReference() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        System.out.println(a.getB() == b);
    }
}
