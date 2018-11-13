package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
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

        String basePackage= ConfigHelper.getAppBasePackage ();
        CLASS_SET= ClassUtil.getClassSet (basePackage);
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
//        Set<Class<?>> classSet=new HashSet<> ();
//        for (Class<?> cls:CLASS_SET) {
//            if (cls.isAnnotationPresent (Service.class)){
//                classSet.add (cls);
//            }
//        }
//        return classSet;
        return getClassSetByAnnotation (Service.class);
    }

    /**
     * 获取应用包名下所有Controller
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
//        Set<Class<?>> classSet=new HashSet<> ();
//        for (Class<?> cls:CLASS_SET) {
//            if (cls.isAnnotationPresent (Controller.class)){
//                classSet.add (cls);
//            }
//        }
//        return classSet;
        return getClassSetByAnnotation (Controller.class);
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

    /**
     * 获取应用包名下某父类的（或接口）的所有子类（或实现类）
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet=new HashSet<> ();
        for (Class<?> cls:CLASS_SET) {
            //isAssignableFrom:如果调用这个方法的class或接口 与 参数cls表示的类或接口相同，或者是参数cls表示的类或接口的父类，则返回true
            if (superClass.isAssignableFrom (cls)&&!superClass.equals (cls)){
                classSet.add (cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     * @param annotation
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotation){
        Set<Class<?>> classSet=new HashSet<> ();
        for (Class<?> cls:CLASS_SET) {
            if (cls.isAnnotationPresent (annotation)){
                classSet.add (cls);
            }

        }
        return classSet;
    }

}
