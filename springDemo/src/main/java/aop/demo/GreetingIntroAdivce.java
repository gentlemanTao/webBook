package aop.demo;


import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * @Author: Gentleman
 * @Date: 2018/11/9 23:59
 * @Description:引入增强
 **/
@Component
public class GreetingIntroAdivce extends DelegatingIntroductionInterceptor {

    public void saySorry(String name) {
        System.out.println ("Sorry! "+name);
    }
}
