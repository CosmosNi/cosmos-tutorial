package com.cosmos.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.annotation
 * @ClassName: JsonParam
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 13:17
 * @Version: 1.0
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParam {

    String value();

    boolean required() default true;

    String defaultValue() default "";
}

