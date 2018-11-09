package aop.advisor;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:37
 * @Description:切面
 **/
public class Client {
    public static void main(String[] args) {
        //切面
        //Hello Jack
        //Advictor GreetingAroundAdvice Before
        //Good Moring ! Jack
        //Advictor GreetingAroundAdvice After

        ApplicationContext context=new ClassPathXmlApplicationContext ("/aop/advisor/spring.xml");
        GreetingImpl greeting= (GreetingImpl) context.getBean ("greetingProxy");
        greeting.sayHello ("Jack");
        greeting.goodMoring ("Jack");
    }
}
