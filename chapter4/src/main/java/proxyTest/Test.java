package proxyTest;

/**
 * @Author: Gentleman
 * @Date: 2018/11/1 0:24
 * @Description:
 **/
public class Test {

    public static void main(String[] args) {
        Hello helloProxy=new HelloProxy ();
        helloProxy.say ("Jack");
        System.out.println ();

        Hello HelloImplProxy=DynamicProxy.getObjectProxy (new HelloImpl ());
        HelloImplProxy.say ("Jack1");
        System.out.println ();

        HelloImpl helloCGLibProxy=CGLibProxy.getObjectProxy (HelloImpl.class);
        helloCGLibProxy.say ("Jack1");
        System.out.println ();

        HelloImplTest HelloImplTestCGLibProxy=CGLibProxy.getObjectProxy (HelloImplTest.class);
        HelloImplTestCGLibProxy.say ("Jack1");
        HelloImplTestCGLibProxy.yesSay ("test");
        System.out.println ();
        helloCGLibProxy.say ("Jack1");
        helloCGLibProxy.noSay ("test");
    }
}
