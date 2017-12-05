package com.bawei.test.injectutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1：类的用途
 * 2：@author 张倩
 * 3:@date 2017/12/5 14:49
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindAction {
    int click() default 0;
}
