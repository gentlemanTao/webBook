package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Gentleman
 * @Date: 2018/11/13 21:56
 * @Description:
 **/
public final class AopHellper {
    private static final Logger log= LoggerFactory.getLogger (BeanHelper.class);

    static {
        try {
            Map<Class<?>,Set<Class<?>>> proxyMap=createProxyMap ();
            Map<Class<?>,List<Proxy>> targetMap=createTargetMap (proxyMap);
            for (Map.Entry<Class<?>,List<Proxy>> targetEntry:targetMap.entrySet ()){
                Class<?> targetClass=targetEntry.getKey ();
                List<Proxy> proxyList=targetEntry.getValue ();
                Object proxy= ProxyManager.createProxy (targetClass,proxyList);
                BeanHelper.setBean (targetClass,proxy);
            }
        }catch (Exception e){
            log.error ("aop failure",e);
        }
    }

    /**
     * 带有Aspect注解的所有类
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect)throws Exception{
        Set<Class<?>> targetClassSet=new HashSet<> ();
        Class<? extends Annotation> annotation=aspect.value ();
        if (annotation!=null&&!annotation.equals (Aspect.class)){
            targetClassSet.addAll (ClassHelper.getClassSetByAnnotation (annotation));
        }
        return targetClassSet;
    }

    /**
     * 创建代理类与目标类集合之间的映射关系
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap()throws Exception{
        Map<Class<?>,Set<Class<?>>> proxyMap=new ConcurrentHashMap<> ();
        //获取代理类的集合
        Set<Class<?>> proxyClassSet= ClassHelper.getClassSetBySuper (AspectProxy.class);
        for (Class<?> proxyClass:proxyClassSet) {
            if (proxyClass.isAnnotationPresent (Aspect.class)){
                Aspect aspect=proxyClass.getAnnotation (Aspect.class);
                Set<Class<?>> targetClassSet=createTargetClassSet(aspect);
                proxyMap.put (proxyClass,targetClassSet);
            }
        }
        return proxyMap;
    }

    /**
     * 目标类与代理对象列表之间的关系
     * @param proxyMap
     * @return
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>,List<Proxy>> targetMap=new ConcurrentHashMap<> ();
        for (Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet ()){
            Class<?> proxyClass=proxyEntry.getKey ();
            Set<Class<?>> targetClassSet=proxyEntry.getValue ();
            for(Class<?> targetClass:targetClassSet){
                Proxy proxy= (Proxy) proxyClass.newInstance ();
                if (targetMap.containsKey (targetClass)){
                    targetMap.get (targetClass).add (proxy);
                }else {
                    List<Proxy> proxyList=new ArrayList<> ();
                    proxyList.add (proxy);
                    targetMap.put (targetClass,proxyList);
                }
            }
        }
        return targetMap;
    }
}
