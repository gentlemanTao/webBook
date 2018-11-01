package proxyTest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/2 0:48
 * @Description:动态代理生成类
 **/
public class CGLibProxy implements MethodInterceptor {
    private static CGLibProxy cgLibProxy=new CGLibProxy ();
    private CGLibProxy(){
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before ();
        Object result=methodProxy.invokeSuper (o,objects);
        after ();
        return result;
    }

    private void before(){
        System.out.println ("CGLibProxy Before");
    };

    private void after(){
        System.out.println ("CGLibProxy After");
    };

    /**
     * 生成代理对象
     * @param <T>
     * @return
     */
    public  static  <T> T getObjectProxy(Class<T> cls){
        return (T) Enhancer.create (cls,cgLibProxy);
    }



}
