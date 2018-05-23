package com.channelapi.web.annotation;


import com.channelapi.web.constant.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface DynamicDataSourceAnnotation {
    //dataSource 自定义注解的参数
    String dataSource() default Constant.DATA_SOURCE_LOCAL;
}
