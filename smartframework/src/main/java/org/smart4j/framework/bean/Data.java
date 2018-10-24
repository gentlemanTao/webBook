package org.smart4j.framework.bean;

/**
 * @Author: Gentleman
 * @Date: 2018/10/25 0:24
 * @Description:返回数据对象
 **/
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
