package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Gentleman
 * @Date: 2018/10/23 23:49
 * @Description:控制器注解
 **/
@Target (ElementType.TYPE)
@Retention (RetentionPolicy.RUNTIME)
public @interface Controller {

}
