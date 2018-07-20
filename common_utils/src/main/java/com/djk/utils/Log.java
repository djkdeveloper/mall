package com.djk.utils;

import java.lang.annotation.*;

/**
 * Created by dujinkai on 2018/6/30.
 * 日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String value() default "操作日志";
}
