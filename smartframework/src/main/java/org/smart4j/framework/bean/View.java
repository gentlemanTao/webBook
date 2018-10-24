package org.smart4j.framework.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Gentleman
 * @Date: 2018/10/25 0:20
 * @Description:返回视图对象
 **/
public class View {
    /**
     * 视图路径
     */
    private String path;
    /**
     * 模型数据
     */
    private Map<String,Object> model;

    public View(String path) {
        this.path = path;
        model=new ConcurrentHashMap<> ();
    }

    public View addModel(String key,Object value){
        model.put (key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
