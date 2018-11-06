package aop.advice;


import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:37
 * @Description:
 **/
public class Client {
    public static void main(String[] args) {
        //Aop增强器可增加多个，按照添加顺序依次进行增强
        //输出结果：
        //前置增强实现类：before
        //Hello jack
        //后置增强：After
        //后置增强Test：After
        //后置增强：After
//        ProxyFactory proxyFactory=new ProxyFactory ();
//        proxyFactory.setTarget (new GreetingImpl ());
//        proxyFactory.addAdvice (new GreetingBeforeAdvice ());
//        proxyFactory.addAdvice (new GreetingAfterAdvice ());
//        proxyFactory.addAdvice (new GreetingAfterTestAdvice ());
//        proxyFactory.addAdvice (new GreetingAfterAdvice ());
//        GreetingImpl greeting= (GreetingImpl) proxyFactory.getProxy ();
//        greeting.sayHello ("jack");

        //Aop增强器：环绕增强
        //环绕增强：前置增强实现类：before
        //Hello jack
        //环绕增强：后置增强实现类：before
        ProxyFactory proxyFactory=new ProxyFactory ();
        proxyFactory.setTarget (new GreetingImpl ());
        proxyFactory.addAdvice (new GreetingBeforeAndAfterAdvice());
        GreetingImpl greeting= (GreetingImpl) proxyFactory.getProxy ();
        greeting.sayHello ("jack");
    }
}
