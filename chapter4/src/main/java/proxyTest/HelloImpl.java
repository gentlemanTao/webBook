package proxyTest;

/**
 * @Author: Gentleman
 * @Date: 2018/11/1 0:21
 * @Description:
 **/
public class HelloImpl implements Hello{
    @Override
    public void say(String name) {
        System.out.println ("Hello ! "+name);
    }

    public void noSay(String name) {
        System.out.println ("noSay Hello ！ "+name);
    }
}
