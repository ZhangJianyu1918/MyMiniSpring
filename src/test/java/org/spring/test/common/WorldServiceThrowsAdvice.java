package org.spring.test.common;

import org.spring.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class WorldServiceThrowsAdvice implements ThrowsAdvice {
    @Override
    public void throwsHandle(Throwable throwable, Method method, Object[] args, Object target) {
        System.out.println("ThrowsAdvice: do something when service throw an Exception");
    }
}
