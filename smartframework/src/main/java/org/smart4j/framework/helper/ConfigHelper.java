package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Properties;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 23:22
 * @Description:属性文件助手类
 **/
public final class ConfigHelper {
    //获取配置文件
    private static Properties CONFIG_PROPS= PropsUtil.loadProps (ConfigConstant.CONFIG_FILE);

    /**
     * 获取JDBC驱动
     * @return
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString (CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    /**
     * 获取JDBC url
     * @return
     */
    public static String getJdbcUrl(){
        return PropsUtil.getString (CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    /**
     * 获取JDBC 用户名
     * @return
     */
    public static String getJdbcUserName(){
        return PropsUtil.getString (CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    /**
     * 获取JDBC 密码
     * @return
     */
    public static String getJdbcPassword(){
        return PropsUtil.getString (CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    /**
     * 获取应用基础包名
     * @return
     */
    public static String getAppBasePackage(){
        return PropsUtil.getString (CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    /**
     * 获取应用jsp路径
     * @return
     */
    public static String getAppJspPath(){
        return PropsUtil.getString (CONFIG_PROPS,ConfigConstant.JDBC_DRIVER,"/WEB-INF/view/");
    }
    /**
     * 获取应用静态资源路径
     * @return
     */
    public static String getAppAssetPath(){
        return PropsUtil.getString (CONFIG_PROPS,ConfigConstant.JDBC_DRIVER,"/asset/");
    }
}
