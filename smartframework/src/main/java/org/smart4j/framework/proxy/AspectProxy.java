package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/12 22:39
 * @Description:切面代理
 **/
public abstract class AspectProxy implements Proxy {

    private static final Logger log= LoggerFactory.getLogger (AspectProxy.class);

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable{
        Object result=null;
        Class<?> cls=proxyChain.getTargetClass ();
        Method method=proxyChain.getTargetMethod ();
        Object[] params=proxyChain.getMethodParams ();
        begin();
        try{
            if (intercept(cls,method,params)){
                before(cls,method,params);
                //获取当前代理链的执行的返回值
                result =proxyChain.doProxyChain ();
                after(cls,method,params,result);
            }
        }catch (Exception e){
            log.error ("proxy failure",e);
            error(cls,method,params,e);
            throw e;
        }
        end ();
        return result;
    }

    public  boolean intercept(Class<?> cls, Method method, Object[] params){
        return true;
    };

    public  void error(Class<?> cls, Method method, Object[] params, Throwable e){

    };

    public  void before(Class<?> cls, Method method, Object[] params) throws Throwable{

    };

    public  void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable{

    };


    public void begin(){

    };

    public void end(){

    };

}
