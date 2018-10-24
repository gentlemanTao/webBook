package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author: Gentleman
 * @Date: 2018/10/25 0:41
 * @Description:编码与解码操作工具类
 **/
public final class CodeUtil {
    private static final Logger log= LoggerFactory.getLogger (CodeUtil.class);

    /**
     * 将URL编码
     * @param source
     * @return
     */
    public static String encodeURL(String source){
        String target;
        try {
            target= URLEncoder.encode (source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error ("encode url failure",e);
            throw new RuntimeException (e);
        }
        return target;
    }
    /**
     * 将URL解码
     * @param source
     * @return
     */
    public static String decodeURL(String source){
        String target;
        try {
            target= URLDecoder.decode (source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error ("decode url failure",e);
            throw new RuntimeException (e);
        }
        return target;
    }


}
