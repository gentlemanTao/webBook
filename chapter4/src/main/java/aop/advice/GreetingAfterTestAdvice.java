package aop.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:34
 * @Description:后置增强
 **/
public class GreetingAfterTestAdvice implements AfterReturningAdvice{

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println ("后置增强Test：After");
    }
}
