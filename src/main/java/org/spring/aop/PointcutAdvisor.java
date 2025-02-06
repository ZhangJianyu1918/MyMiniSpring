package org.spring.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
