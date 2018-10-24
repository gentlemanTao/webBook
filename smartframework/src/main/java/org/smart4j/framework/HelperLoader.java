package org.smart4j.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * @Author: Gentleman
 * @Date: 2018/10/24 23:58
 * @Description:加载相应的Helper类
 **/
public final class  HelperLoader {
    private static final Logger log= LoggerFactory.getLogger (HelperLoader.class);
    public static void init(){
        Class<?>[] classList={
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        log.info (String.valueOf (classList.length));
        for (Class<?> cls:classList) {
            log.info (cls.getName ());
            ClassUtil.loadClass (cls.getName (),true);
        }
    }
}
