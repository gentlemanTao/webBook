package proxyTest;

import java.lang.reflect.Proxy;

/**
 * @Author: Gentleman
 * @Date: 2018/11/1 0:24
 * @Description:
 **/
public class Test {

    public static void main(String[] args) {
//        Hello helloProxy=new HelloProxy ();
//        helloProxy.say ("Jack");


        Hello hello=new HelloImpl ();
        DynamicProxy dynamicProxy=new DynamicProxy (hello);
        //创建代理
        Hello helloProxy= (Hello) Proxy.newProxyInstance (hello.getClass ().getClassLoader (),hello.getClass ().getInterfaces (),dynamicProxy);
        helloProxy.say ("Jack");
    }
}
