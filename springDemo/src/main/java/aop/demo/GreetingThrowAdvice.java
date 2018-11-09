package aop.demo;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/9 22:59
 * @Description:抛出异常增强类
 **/
@Component
public class GreetingThrowAdvice implements ThrowsAdvice{

    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException  throwable){
        System.out.println ("-----------------Throw Exception---------------");
        System.out.println ("Target Class:"+target.getClass ().getName ());
        System.out.println ("Method Name:"+method.getName ());
        System.out.println ("Exception Message:"+ throwable.getMessage ());
        System.out.println ("------------------------------------------------");
    }
}
