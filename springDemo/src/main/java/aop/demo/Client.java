package aop.demo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:37
 * @Description:
 **/
public class Client {
    public static void main(String[] args) {
        //获取Spring context
        ApplicationContext context=new ClassPathXmlApplicationContext ("/aop/demo/spring.xml");
//        //从context根据ID获取代理对象
//        Greeting greeting= (Greeting) context.getBean ("greetingProxy");
//        //调用代理的方法
//        greeting.sayHello ("Jack");

        GreetingImpl greeting1= (GreetingImpl) context.getBean ("greetingIntroductionProxy");
        greeting1.sayHello ("Jack");
        Apology apology=(Apology)greeting1;
        apology.saySorry ("Jack");
    }
}
