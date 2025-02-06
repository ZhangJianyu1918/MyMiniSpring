package org.spring.test.aop;

import org.junit.Before;
import org.junit.Test;
import org.spring.aop.GenericInterceptor;
import org.spring.aop.AdvisedSupport;
import org.spring.aop.MethodMatcher;
import org.spring.aop.TargetSource;
import org.spring.aop.aspectj.AspectJExpressionPointcut;
import org.spring.aop.framework.CglibAopProxy;
import org.spring.aop.framework.JdkDynamicAopProxy;
import org.spring.aop.framework.ProxyFactory;
import org.spring.test.common.*;
import org.spring.test.service.WorldService;
import org.spring.test.service.WorldServiceImpl;
import org.spring.test.service.WorldServiceWithExceptionImpl;

public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.spring.test.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }


    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();

    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testProxyFactory() throws Exception{
        // jdk代理
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();

        // cglib代理
        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testBeforeAdvice() throws Exception {
        WorldServiceBeforeAdvice worldServiceBeforeAdvice = new WorldServiceBeforeAdvice();
        GenericInterceptor interceptor = new GenericInterceptor();
        interceptor.setBeforeAdvice(worldServiceBeforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testAfterAdvice() throws Exception {
        WorldServiceAfterAdvice worldServiceAfterAdvice = new WorldServiceAfterAdvice();
        GenericInterceptor interceptor = new GenericInterceptor();
        interceptor.setAfterAdvice(worldServiceAfterAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testAfterReturningAdvice() throws Exception {
        WorldServiceAfterReturningAdvice worldServiceAfterReturningAdvice = new WorldServiceAfterReturningAdvice();
        GenericInterceptor interceptor = new GenericInterceptor();
        interceptor.setAfterReturningAdvice(worldServiceAfterReturningAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testThrowsAdvice() throws Exception {
        WorldServiceWithExceptionImpl worldServiceWithException = new WorldServiceWithExceptionImpl();
        WorldServiceThrowsAdvice throwsAdvice = new WorldServiceThrowsAdvice();
        GenericInterceptor interceptor = new GenericInterceptor();
        interceptor.setThrowsAdvice(throwsAdvice);
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setTargetSource(new TargetSource(worldServiceWithException));

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

}
