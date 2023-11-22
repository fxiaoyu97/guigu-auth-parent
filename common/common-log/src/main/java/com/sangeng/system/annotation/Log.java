package com.sangeng.system.annotation;

import java.lang.annotation.*;

import com.sangeng.system.enmus.BusinessType;
import com.sangeng.system.enmus.OperatorType;

@Target({ElementType.PARAMETER, ElementType.METHOD}) // 使用的位置，参数和方法
@Retention(RetentionPolicy.RUNTIME) // 什么时候使用，运行时
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;
}
