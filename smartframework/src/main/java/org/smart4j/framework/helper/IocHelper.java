package org.smart4j.framework.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;


/**
 * @Author: Gentleman
 * @Date: 2018/10/24 0:37
 * @Description:依赖注入助手类
 **/
public final class IocHelper {

    private static final Logger log= LoggerFactory.getLogger (IocHelper.class);


    /**
     * 获取所有的bean，
     * 将bean的每一个成员变量进行遍历以看是否有注解
     * 将成员变量注解所对应的Bean实例注入到Bean中
     */
    static {
        //获取所有的Bean类与Bean实例之间的映射关系
        Map<Class<?>,Object> beanMap= BeanHelper.getBeanMap ();
        if (CollectionUtil.isNotEmpty (beanMap)){
            //遍历BeanMap
            for (Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet ()) {
                //获取bean的类和实例
                Class<?> beanClass=beanEntry.getKey ();

                Object beanInstance=beanEntry.getValue ();
                //获取Bean类定义的所有成员变量
                Field[] beanFields=beanClass.getDeclaredFields ();
                if (ArrayUtil.isNotEmpty (beanFields)){
                    //循环判断当前BeanField是否带有Inject注解，以注入bean实例
                    for (Field beanField:beanFields){
                        if (beanField.isAnnotationPresent (Inject.class)){
                            //获取BeanField的类
                            Class<?> beanFieldClass=beanField.getType ();
                            //获取BeanField的实例
                            Object beanFieldInstance=beanMap.get (beanFieldClass);
                            //进行赋值，若不存在则抛出异常
                            if (beanFieldInstance!=null){
                                ReflectionUtil.setField (beanInstance,beanField,beanFieldInstance);
                            }else{
                                log.error ("no class"+beanFieldClass+" find");
                                throw new RuntimeException ("no class"+beanFieldClass+" find");
                            }
                         }
                    }
                }
            }
        }
    }
}
