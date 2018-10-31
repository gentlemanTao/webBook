package proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Gentleman
 * @Date: 2018/11/1 0:29
 * @Description:动态代理
 **/
public class DynamicProxy implements InvocationHandler{
    private Object target;

    public DynamicProxy(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before ();
        //使用了反射
        Object result=method.invoke (target,args);
        after ();
        return result;
    }

    /**
     * 生成代理对象
     * @param <T>
     * @return
     */
    private <T> T creterProxy(){
        return (T) Proxy.newProxyInstance (target.getClass ().getClassLoader (),target.getClass ().getInterfaces (),this);
    }
    private void before(){
        System.out.println ("DynamicProxy Before");
    };

    private void after(){
        System.out.println ("DynamicProxy After");
    };

    //返回被代理后的对象
    public  static <T> T getObjectProxy(Object object){
        DynamicProxy dynamicProxy=new DynamicProxy (object);
        return dynamicProxy.creterProxy ();
    }
}
