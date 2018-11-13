package org.smart4j.framework.proxy;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/12 22:55
 * @Description:
 **/
public class TestProxyOne extends AspectProxy {

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println ("TestProxyOne Before");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        System.out.println ("TestProxyOne after");
    }
}
