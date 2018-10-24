package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Gentleman
 * @Date: 2018/10/24 23:20
 * @Description:控制器助手类
 **/
public final class ControllerHelper {

    private static final Logger log= LoggerFactory.getLogger (ControllerHelper.class);

    private static final Map<Request,Handler> ACTION_MAP=new ConcurrentHashMap<> ();

    static {
        //获取Controller类集合
        Set<Class<?>> controllerSet=ClassHelper.getControllerClassSet ();
        log.info (String.valueOf (controllerSet.size ()));
        if (CollectionUtil.isNotEmpty (controllerSet)){
            //循环遍历controllerClass集合
            for (Class<?> controllerClass:controllerSet) {
                //获取controllerClass类中的方法
                Method[] methods=controllerClass.getMethods ();
                if (ArrayUtil.isNotEmpty (methods)){
                    //循环遍历controllerClass类中的方法，判断是否存在Action注解
                    for (Method method:methods) {
                        //判断是否存在Action注解
                        if (method.isAnnotationPresent (Action.class)){
                            Action action=method.getAnnotation (Action.class);
                            //获取Action的value
                            String mapping=action.value ();
                            log.info (mapping);
                            if (mapping.matches ("\\w+:/\\w*")){
                                String[] arrays=mapping.split (":");
                                if (ArrayUtil.isNotEmpty (arrays)&&arrays.length==2){
                                    //获取请求方法与路径
                                    String requestMethod=arrays[0];
                                    String requestPath=arrays[1];
                                    log.info (mapping);
                                    Request request=new Request (requestMethod,requestPath);
                                    Handler handler=new Handler (controllerClass,method);
                                    ACTION_MAP.put (request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info (String.valueOf (ACTION_MAP));
    }

    /**
     * 获取Handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler (String requestMethod,String requestPath){
        log.info (String.valueOf (ACTION_MAP));
        Request request=new Request (requestMethod,requestPath);
        return ACTION_MAP.get (request);
    }
}
