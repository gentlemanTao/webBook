package aop.advice;


import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:37
 * @Description:
 **/
public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory=new ProxyFactory ();
        proxyFactory.setTarget (new GreetingImpl ());
        proxyFactory.addAdvice (new GreetingBeforeAdvice ());
        proxyFactory.addAdvice (new GreetingAfterAdvice ());
        GreetingImpl greeting= (GreetingImpl) proxyFactory.getProxy ();
        greeting.sayHello ("jack");
    }
}
