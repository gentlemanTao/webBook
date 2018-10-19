package org.smart4j.framework;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 23:13
 * @Description:提供相关配置常量
 **/
public interface ConfigConstant {
    //配置文件路径
    String CONFIG_FILE="smart.properties";
    //数据库配置
    String JDBC_DRIVER="smart.frameword.jdbc.driver";
    String JDBC_URL="smart.frameword.jdbc.url";
    String JDBC_USERNAME="smart.frameword.jdbc.username";
    String JDBC_PASSWORD="smart.frameword.jdbc.password";

    //应用配置
    String APP_BASE_PACKAGE="smart.frameword.app.base_package";
    String APP_JSP_PATH="smart.frameword.app.jsp_path";
    String APP_ASSET_PATH="smart.frameword.app.asset_path";

}
