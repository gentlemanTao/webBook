package aop.aspectJ;

import org.springframework.stereotype.Component;

/**
 * @Author: Gentleman
 * @Date: 2018/11/6 21:35
 * @Description:实体类
 **/
@Component
public class GreetingImpl implements Greeting {

    public void sayHello(String name){
        System.out.println ("Hello "+name);
    }

    @Tag
    public void goodMoring(String name){
        System.out.println ("Good Moring ! "+name);
    };

    public void goodNight(String name){
        System.out.println ("Good Night ! "+name);
    };

}
