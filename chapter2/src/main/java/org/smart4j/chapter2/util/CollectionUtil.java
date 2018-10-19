package org.smart4j.chapter2.util;

import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 1:10
 * @Description:集合工具类
 **/
public class CollectionUtil {

    /**
     * 判断Collection是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtil.isEmpty (collection);
    }

    /**
     * 判断Collection是否为非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty (collection);
    }

    /**
     * 判断Map是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty (map);
    }
    /**
     * 判断Map是否为非空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty (map);
    }
}
