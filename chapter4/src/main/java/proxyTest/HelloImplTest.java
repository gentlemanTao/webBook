package proxyTest;

/**
 * @Author: Gentleman
 * @Date: 2018/11/1 0:21
 * @Description:
 **/
public class HelloImplTest implements Hello{
    @Override
    public void say(String name) {
        System.out.println ("Hello !你好！ "+name);
    }

    public void yesSay(String name) {
        System.out.println ("yesSay Hello !你好！ "+name);
    }
}
