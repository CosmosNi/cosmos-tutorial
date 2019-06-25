package com.cosmos.log.commmon.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.annotation
 * @ClassName: Log
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 13:17
 * @Version: 1.0
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    boolean saveParams() default true;

    String operFunction() default "";

    String description() default "";


}
