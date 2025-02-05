package org.spring.test.aop;

import org.junit.jupiter.api.Test;
import org.spring.aop.AdvisedSupport;
import org.spring.aop.MethodMatcher;
import org.spring.aop.TargetSource;
import org.spring.aop.aspectj.AspectJExpressionPointcut;
import org.spring.aop.framework.JdkDynamicAopProxy;
import org.spring.test.common.WorldServiceInterceptor;
import org.spring.test.service.WorldService;
import org.spring.test.service.WorldServiceImpl;

public class DynamicProxyTest {

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.spring.test.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();

    }
}
