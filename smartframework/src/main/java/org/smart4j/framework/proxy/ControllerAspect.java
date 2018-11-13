package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/11/12 23:21
 * @Description:拦截Controller的所有方法
 **/
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger log= LoggerFactory.getLogger (ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        log.info ("-----------begin--------------");
        log.info (String.format ("class: %s",cls.getName ()));
        log.info (String.format ("method: %s",method.getName ()));
        begin=System.currentTimeMillis ();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        log.info (String.format ("time: %dms",System.currentTimeMillis ()-begin));
        log.info ("-----------end--------------");
    }
}
