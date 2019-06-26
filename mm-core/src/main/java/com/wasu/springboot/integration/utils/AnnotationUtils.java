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

    public static boolean chechAnnotationExist(Class obj, Class<? extends Annotation>... expectAnnotations) {
        try{
            Annotation[] annotations=obj.getAnnotations();
            for(Annotation anno:annotations){
                for(Class<? extends Annotation> expectAnno : expectAnnotations){
                    if(anno.annotationType().equals(expectAnno)){
                        return true;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean chechAnnotationExist(Class obj,String method,Class<? extends Annotation> annotation) {
        try{
            for(Method m:obj.getMethods()){
                if(m.getName().equals(method)){
                    for(Annotation anno:m.getDeclaredAnnotations()){
                        if(anno.annotationType().equals(annotation)){
                            return true;
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
