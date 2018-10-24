package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: Gentleman
 * @Date: 2018/10/25 0:35
 * @Description:流操作工具类
 **/
public class StreamUtil {

    private static final Logger log= LoggerFactory.getLogger (StreamUtil.class);

    /**
     * 从输入流中获取字符串
     * @param is
     * @return
     */
    public static String getString(InputStream is) {
        StringBuilder stb=new StringBuilder ();
        try {
            BufferedReader reader=new BufferedReader (new InputStreamReader (is));
            String line;
            while((line=reader.readLine ())!=null){
                stb.append (line);
            }
        } catch (Exception e) {
            log.error ("get String failure",e);
            throw new RuntimeException (e);
        }
        return stb.toString ();
    }
}
