package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Gentleman
 * @Date: 2018/10/23 23:56
 * @Description:类操作助手类
 **/
public final class ClassHelper {

    private static final Logger log= LoggerFactory.getLogger (ClassHelper.class);
    /**
     * 定义类集合（用于存放所加载的类）
     */
    private static final Set<Class<?>> CLASS_SET;

    static {

        String basePackage=ConfigHelper.getAppBasePackage ();
        CLASS_SET= ClassUtil.getClassSet (basePackage);
        log.info (String.valueOf (CLASS_SET.size ()));
    }

    /**
     * 获取应用包名下的所有类
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有的Service类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet=new HashSet<> ();
        for (Class<?> cls:CLASS_SET) {
            if (cls.isAnnotationPresent (Service.class)){
                classSet.add (cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有Controller
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet=new HashSet<> ();
        for (Class<?> cls:CLASS_SET) {
            if (cls.isAnnotationPresent (Controller.class)){
                classSet.add (cls);
            }
        }
        log.info (String.valueOf (classSet.size ()));
        return classSet;
    }

    /**
     * 获取应用包名下所有Bean类（包括Service,Controller）
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet=new HashSet<> ();
        classSet.addAll (getServiceClassSet ());
        classSet.addAll (getControllerClassSet ());
        return classSet;
    }


}
