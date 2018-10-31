package proxyTest;

/**
 * @Author: Gentleman
 * @Date: 2018/11/1 0:24
 * @Description:
 **/
public class Test {

    public static void main(String[] args) {
//        Hello helloProxy=new HelloProxy ();
//        helloProxy.say ("Jack");


        Hello helloProxy=DynamicProxy.getObjectProxy (new HelloImpl ());
        helloProxy.say ("Jack1");
    }
}
