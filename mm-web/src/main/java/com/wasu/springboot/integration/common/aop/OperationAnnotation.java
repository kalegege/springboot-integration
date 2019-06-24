package com.wasu.springboot.integration.common.aop;

import com.wasu.springboot.integration.enums.OperationTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationAnnotation {
    String operationName();

    OperationTypeEnum operateType();
}
