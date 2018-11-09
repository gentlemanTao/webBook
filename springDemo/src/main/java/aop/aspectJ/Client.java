package aop.aspectJ;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:37
 * @Description:切面
 **/
public class Client {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext ("/aop/aspectJ/spring.xml");
        Greeting greeting= (Greeting) context.getBean ("greetingImpl");
      //  greeting.goodMoring ("Jack");
        //引入增强
        Apology apology= (Apology) greeting;
        apology.saySorry ("Jack");
        TestImpl test= (TestImpl) context.getBean ("testImpl");
        Apology apologyTest= (Apology) test;
        apologyTest.saySorry ("Test Jack");
    }
}
