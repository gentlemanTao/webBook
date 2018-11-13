package org.smart4j.framework.proxy;

/**
 * @Author: Gentleman
 * @Date: 2018/11/11 18:11
 * @Description:代理接口
 **/
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
