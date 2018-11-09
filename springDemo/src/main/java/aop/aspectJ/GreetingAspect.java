package aop.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: Gentleman
 * @Date: 2018/11/10 0:39
 * @Description:基于注解切面：通过AspectJ execution 表达式拦截方法
 * 环绕增强比前置增强和后置增强先进行
 * 多个相同的前置后置增强按照在类内的顺序依次执行
 * 多个环绕增强：前置增强按类内的顺序依次执行，后置增强按类内的顺序倒序执行
 **/
@Aspect
@Component
public class GreetingAspect {

    /**
     * 环绕增强
     * @param point
     * @return
     * @throws Throwable
     */
    @Around ("execution(* aop.aspectJ.GreetingImpl.*(..))")
    public Object aroundadvice1(ProceedingJoinPoint point) throws Throwable{
        before("GreetingAspect Aroundadvice1 ");
        Object result=point.proceed ();
        after("GreetingAspect Aroundadvice1 ");
        return result;
    }

    /**
     * 环绕增强
     * @param point
     * @return
     * @throws Throwable
     */
    @Around ("execution(* aop.aspectJ.GreetingImpl.*(..))")
    public Object aroundadvice2(ProceedingJoinPoint point) throws Throwable{
        before("GreetingAspect Aroundadvice2 ");
        Object result=point.proceed ();
        after("GreetingAspect Aroundadvice2 ");
        return result;
    }

    /**
     * 环绕增强
     * @param point
     * @return
     * @throws Throwable
     */
    @Around ("execution(* aop.aspectJ.GreetingImpl.*(..))")
    public Object aroundadvice3(ProceedingJoinPoint point) throws Throwable{
        before("GreetingAspect Aroundadvice3 ");
        Object result=point.proceed ();
        after("GreetingAspect Aroundadvice3 ");
        return result;
    }

    /**
     * 前置增强
     * @param point
     * @return
     * @throws Throwable
     */
    @Before ("execution(* aop.aspectJ.GreetingImpl.*(..))")
    public void beforeAdvice(JoinPoint point) throws Throwable{
        before("GreetingAspect BeforeAdvice ");
    }

    /**
     * 后置增强
     * @param point
     * @return
     * @throws Throwable
     */
    @After ("execution(* aop.aspectJ.GreetingImpl.*(..))")
    public void afterAdvice(JoinPoint point) throws Throwable{
        after("GreetingAspect AfterAdvice ");
    }

    /**
     *  基于注解的环绕增强
     */
    @Around ("@annotation(aop.aspectJ.Tag)")
    public Object aroundannotationadvice3(ProceedingJoinPoint point) throws Throwable{
        before("GreetingAspect aroundannotationadvice ");
        Object result=point.proceed ();
        after("GreetingAspect aroundannotationadvice  ");
        return result;
    }

    /**
     * 引入增强；value,可以用通配符去匹配对应的类
     */
    @DeclareParents (value = "aop.aspectJ.*Impl",defaultImpl=ApologyImpl.class)
    private Apology apology;

    private void before(String message){
        System.out.println (message+"   Before");
    };

    private void after(String message){
        System.out.println (message+"   After");
    };
}
