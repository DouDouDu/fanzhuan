package com.bawei.test.injectutil2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1：类的用途
 * 2：@author 张倩
 * 3:@date 2017/12/5 15:31
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewBind {
    int value() default 0;
}
