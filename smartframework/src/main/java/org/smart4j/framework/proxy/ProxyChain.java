package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Gentleman
 * @Date: 2018/11/11 18:12
 * @Description:代理链：将多个代理通过一条链子串起来，一个个地去执行
 **/
public class ProxyChain {
    /**
     * 目标类
     */
    private final Class<?> targetClass;
    /**
     * 目标对象
     */
    private final Object   targetObject;
    /**
     * 目标方法
     */
    private final Method   targetMethod;
    /**
     * 目标方法代理
     */
    private final MethodProxy methodProxy;
    /**
     * 目标方法参数
     */
    private final Object[] methodParams;

    private List<Proxy> proxyList=new ArrayList<> ();

    private int proxyIndex=0;

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    /**
     * 递归调用代理
     * @return
     * @throws Throwable
     */
    public Object doProxyChain() throws Throwable{
        Object methodResult;
        //递归从第一个代理中，将该代理链传递下去，直到方法执行获取返回值，并传递回去
        if (proxyIndex<proxyList.size ()){
            methodResult=proxyList.get (proxyIndex++).doProxy (this);
        }else {
            methodResult=methodProxy.invokeSuper (targetObject,methodParams);
        }
        return methodResult;
    }
}
