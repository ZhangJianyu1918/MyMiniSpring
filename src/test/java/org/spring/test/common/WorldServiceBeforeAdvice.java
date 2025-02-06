package org.spring.test.common;

import org.spring.aop.BeforeAdvice;

import java.lang.reflect.Method;

public class WorldServiceBeforeAdvice implements BeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice: Do something before running service");
    }
}
