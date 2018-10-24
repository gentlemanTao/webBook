package org.smart4j.chapter3.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 0:46
 * @Description:字符串工具类
 **/
public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String  str){
        if (str!=null){
            str=str.trim ();
        }
        return StringUtils.isEmpty (str);
    }
    /**
     * 判断字符串是否为非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String  str){
        return  !StringUtils.isEmpty (str);
    }
}
