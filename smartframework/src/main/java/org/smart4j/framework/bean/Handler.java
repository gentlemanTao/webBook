package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * @Author: Gentleman
 * @Date: 2018/10/24 23:18
 * @Description:封装Action信息
 **/
public class Handler {
    /**
     * Controller类
     */
    private Class<?> controllerClass;
    /**
     * Action方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass,Method actionMethod){
        this.controllerClass=controllerClass;
        this.actionMethod=actionMethod;
    };
    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
