package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.ReflectionUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Gentleman
 * @Date: 2018/10/24 0:24
 * @Description:Bean助手类
 **/
public final class BeanHelper {
    private static final Logger log= LoggerFactory.getLogger (BeanHelper.class);
    /**
     * 定义Bean映射（用于存放Bean类与Bean实例的映射关系）
     */
    private static final Map<Class<?>,Object> BEAN_MAP=new ConcurrentHashMap<> ();

    static {
        Set<Class<?>> beanClassSet= ClassHelper.getBeanClassSet ();
        for (Class<?> cls:beanClassSet) {
            Object obj= ReflectionUtil.newInstance (cls);
            BEAN_MAP.put (cls,obj);
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 根据Bean类获取Bean的实例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey (cls)){
            throw new RuntimeException ("can nor bean by class:"+cls);
        }
        return (T)BEAN_MAP.get (cls);
    }

    /**
     * 设置Bean实例
     * @param cls
     * @param object
     */
    public static void setBean(Class<?> cls,Object object){
        BEAN_MAP.put (cls,object);
    }
}
