package org.smart4j.chapter2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 0:37
 * @Description:属性文件工具类
 **/
public final class PropsUtil {

//    private static final Logger log= LoggerFactory.getLogger (PropsUtil.class);

    /**
     * 加载属性文件
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties prop=null;
        InputStream is=null;
        try {
            //读取配置文件创建输入流
            is=Thread.currentThread ().getContextClassLoader ().getResourceAsStream (fileName);
            if (is==null){
                throw new FileNotFoundException (fileName+"file is not found");
            }
            prop=new Properties ();
            //加载配置文件
            prop.load (is);
        }catch (IOException e){
            e.printStackTrace ();
       //     log.error ("load properties file failure",e);
        }finally {
            if (is !=null){
                try {
                    is.close ();
                }catch (IOException e) {
                    e.printStackTrace ();
     //               log.error ("close input stream failure", e);
                }
            }
        }
        return prop;
    }

    /**
     * 获取字符型属性(默认值为空字符串)
     * @param props
     * @param key
     * @return
     */
    public static String getString(Properties props,String key){
        return getString (props,key,"");
    }
    /**
     * 获取字符型属性(可指定默认值)
     * @param props
     * @param key
     * @param defaultvalue
     * @return
     */
    public static String getString(Properties props,String key,String defaultvalue){
        String value=defaultvalue;
        if (props.containsKey (key)){
            value=props.getProperty (key);
        }
        return defaultvalue;
    }
    /**
     * 获取数值型属性(默认值为0)
     * @param props
     * @param key
     * @return
     */
    public static int getInt(Properties props,String key){
        return getInt (props,key,0);
    }
    /**
     * 获取数值型属性(可指定默认值)
     * @param props
     * @param key
     * @param defaultvalue
     * @return
     */
    public static int getInt(Properties props,String key,int defaultvalue){
        int value=defaultvalue;
        if (props.containsKey (key)){
            value=CastUtil.castInt (props.getProperty (key));
        }
        return defaultvalue;
    }
    /**
     * 获取数值型属性(默认值为0)
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props,String key){
        return getBoolean (props,key,false);
    }
    /**
     * 获取数值型属性(可指定默认值)
     * @param props
     * @param key
     * @param defaultvalue
     * @return
     */
    public static boolean getBoolean(Properties props,String key,boolean defaultvalue){
        boolean value=defaultvalue;
        if (props.containsKey (key)){
            value=CastUtil.castBoolean (props.getProperty (key));
        }
        return defaultvalue;
    }
}
