package org.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GenericInterceptor implements MethodInterceptor {

    private BeforeAdvice beforeAdvice;
    private AfterAdvice afterAdvice;
    private AfterReturningAdvice afterReturningAdvice;
    private ThrowsAdvice throwsAdvice;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = null;
        try {
            if (beforeAdvice != null) {
                beforeAdvice.before(
                        methodInvocation.getMethod(),
                        methodInvocation.getArguments(),
                        methodInvocation.getThis()
                );
            }
            result = methodInvocation.proceed();
        } catch (Exception throwable) {
            if (throwsAdvice != null) {
                throwsAdvice.throwsHandle(
                        throwable,
                        methodInvocation.getMethod(),
                        methodInvocation.getArguments(),
                        methodInvocation.getThis()
                );
            }
        } finally {
            if (afterAdvice != null) {
                afterAdvice.after(
                        methodInvocation.getMethod(),
                        methodInvocation.getArguments(),
                        methodInvocation.getThis());
            }
        }

        if (afterReturningAdvice != null) {
            afterReturningAdvice.afterReturning(
                    result,
                    methodInvocation.getMethod(),
                    methodInvocation.getArguments(),
                    methodInvocation.getThis());
        }
        return result;
    }

    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }

    public void setAfterReturningAdvice(AfterReturningAdvice afterReturningAdvice) {
        this.afterReturningAdvice = afterReturningAdvice;
    }

    public void setThrowsAdvice(ThrowsAdvice throwsAdvice) {
        this.throwsAdvice = throwsAdvice;
    }
}
