package org.spring.test.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Do something before running world service");
        Object proceed = methodInvocation.proceed();
        System.out.println("Do something after running world service");
        return proceed;
    }
}
