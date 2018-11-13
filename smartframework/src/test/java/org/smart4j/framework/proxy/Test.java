package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Gentleman
 * @Date: 2018/11/12 22:52
 * @Description:递归调用代理链
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        List<Proxy> proxyList=new ArrayList<> ();
        proxyList.add (new TestProxyOne ());
        proxyList.add (new TestProxyTwo ());
        proxyList.add (new TestProxyThree ());
        Method sayHello=HelloWord.class.getMethod ("sayHello", String.class);
        Object[] objects={"Jack"};
        HelloWord helloWord= ProxyManager.createProxy (HelloWord.class,proxyList);
        helloWord.sayHello ("Jack");
    }
}
