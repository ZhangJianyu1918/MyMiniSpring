package org.spring.test.common;

import org.spring.aop.AfterAdvice;

import java.lang.reflect.Method;

public class WorldServiceAfterAdvice implements AfterAdvice {
    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("After: Do something after world service");
    }
}
