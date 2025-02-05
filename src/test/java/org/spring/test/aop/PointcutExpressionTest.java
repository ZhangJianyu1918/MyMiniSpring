package org.spring.test.aop;

import org.junit.jupiter.api.Test;
import org.spring.aop.aspectj.AspectJExpressionPointcut;
import org.spring.test.service.HelloService;

import java.lang.reflect.Method;

public class PointcutExpressionTest {

    @Test
    public void testPointcutExpression() throws Exception {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution (* org.spring.test.service.HelloService.*(..))");
        Class<HelloService> helloServiceClass = HelloService.class;
        Method method = helloServiceClass.getDeclaredMethod("sayHello");

        System.out.println(aspectJExpressionPointcut.matches(helloServiceClass));
        System.out.println(aspectJExpressionPointcut.matches(method, helloServiceClass));

    }
}
