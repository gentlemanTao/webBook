package aop.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:30
 * @Description:前置增强类
 **/
public class GreetingBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println ("前置增强实现类：before");
    }
}
