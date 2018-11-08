package aop.proxyTest;

/**
 * @Author: Gentleman
 * @Date: 2018/11/1 0:22
 * @Description:HelloImpl的代理类
 **/
public class HelloProxy implements Hello{

    private Hello hello;

    public HelloProxy(){
        hello=new HelloImpl ();
    }
    @Override
    public void say(String name) {
        before();
        hello.say (name);
        after();
    }

    private void before(){
        System.out.println ("Before");
    };

    private void after(){
        System.out.println ("After");
    };
}
