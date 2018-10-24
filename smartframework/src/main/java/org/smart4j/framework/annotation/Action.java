package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Gentleman
 * @Date: 2018/10/23 23:53
 * @Description:Action方法注解
 **/

/**
 * @Target元注解：表示该注解可以用在什么地方
 * ElementType参数：
 * TYPE:用于类，接口，枚举,但不用于枚举值。
 * FIELD: 字段上，包括枚举值
 * METHOD:方法，不包括构造方法
 * PARAMETER:方法的参数
 * CONSTRUCTOR:构造方法
 * LOCAL_VARIABLE:本地变量或catch语句
 * ANNOTATION_TYPE:用于注解
 * PACKAGE:Java包
 * TYPE_PARAMETER:泛型，1.8新增
 * TYPE_USE:任意类类型，1.8新增
 *
 * @Retention注解：表示需要在什么级别保存该注解信息
 * RetentionPolicy参数
 * SOURCE:在源文件中有效（即源文件保留）
 * CLASS:在class文件中有效（即class保留）
 * RUNTIME:在运行时有效（即运行时保留）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /**
     * 请求方法与路径
     */
    String value();
}
