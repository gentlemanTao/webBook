package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @Author: Gentleman
 * @Date: 2018/10/24 21:26
 * @Description:数组工具类
 **/
public class ArrayUtil {

    /**
     * 判断数据是否为非空
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array){
        return ArrayUtils.isNotEmpty (array);
    }

    /**
     * 判断数组是否为空
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty (array);
    }
}
