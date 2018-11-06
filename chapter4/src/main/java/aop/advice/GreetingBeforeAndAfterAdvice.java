package aop.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/7 0:28
 * @Description:
 **/
public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice,AfterReturningAdvice{
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println ("环绕增强：后置增强实现类：before");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println ("环绕增强：前置增强实现类：before");
    }
}
