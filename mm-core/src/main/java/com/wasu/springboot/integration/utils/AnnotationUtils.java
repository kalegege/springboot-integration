package com.wasu.springboot.integration.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationUtils extends org.springframework.core.annotation.AnnotationUtils {
    public static Object getPropertyAnnotationValue(Class obj, String propertyName, Class<? extends Annotation> annotation, String annotationPropertyName) {
        try {
            for (Field f : obj.getDeclaredFields()) {
                if (f.getName().equals(propertyName))
                    for (Annotation anno : f.getDeclaredAnnotations())
                        if (anno.annotationType() == annotation) {
                            Method method = anno.getClass().getDeclaredMethod(annotationPropertyName, new Class[0]);
                            return method.invoke(anno, new Object[0]);
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getMethodAnnotationValue(Class obj,String method,Class<? extends Annotation> annotation,String annotationPropertyName){
        try {
            for (Method f : obj.getMethods()) {
                if (f.getName().equals(method))
                    for (Annotation anno : f.getDeclaredAnnotations())
                        if (anno.annotationType() == annotation) {
                            Method mtd = anno.getClass().getDeclaredMethod(annotationPropertyName, new Class[0]);
                            return mtd.invoke(anno, new Object[0]);
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
