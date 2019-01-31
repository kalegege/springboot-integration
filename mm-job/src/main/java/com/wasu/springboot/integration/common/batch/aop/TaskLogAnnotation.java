package com.wasu.springboot.integration.common.batch.aop;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Order(Integer.MAX_VALUE)
public @interface TaskLogAnnotation {
    String taskName();
}
