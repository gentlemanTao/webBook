package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @Author: Gentleman
 * @Date: 2018/10/24 23:14
 * @Description:封装请求信息
 **/
public class Request {

    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求路径
     */
    private String requestPath;

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    @Override
    public boolean equals(Object Obj) {
        return EqualsBuilder.reflectionEquals (this,Obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode (this);
    }
}
