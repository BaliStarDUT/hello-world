package com.james.springboot.web.intercepter;

/**
 * author: yang
 * datetime: 2021/9/9 15:36
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsRequired {
    String[] value();
}

