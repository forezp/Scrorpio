package io.github.forezp.scrorpio.proxy;

import com.google.common.collect.Maps;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.forezp.scrorpio.proxy.ProxyMode.*;


/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-25
 **/
public abstract class AbstractBeanPostProcessor implements BeanPostProcessor {

    private Map<String, List<Class>> beansToProcess = Maps.newHashMap();
    private Class<? extends Annotation> annotation;
    private ProxyMode proxyMode = ALL;

    public abstract  Class<? extends Annotation> getAnnoatation();

    /**
     * 处理类上的注解
     *
     * @param target
     * @param annotation
     * @return
     */
    public abstract Object postProcessClz(Object target, Annotation annotation);

    /**
     * 处理方法上的注解
     *
     * @param target
     * @param method
     * @param annotation
     * @return
     */
    public abstract Object postProcessMethod(Object target, Method method, Annotation annotation);

    /**
     * 处理作用域上的注解
     *
     * @param target
     * @param field
     * @param annotation
     * @return
     */
    public abstract Object postProcessField(Object target, Field field, Annotation annotation);


    public AbstractBeanPostProcessor(ProxyMode proxyMode) {
        this.annotation = getAnnoatation();
        this.proxyMode = proxyMode;
    }

    public AbstractBeanPostProcessor( ) {
        this.annotation = getAnnoatation();

    }



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {


        Class clazz = bean.getClass();
        do {

            if (containsClass( proxyMode )) {
                //类上的注解进行操作
                if (clazz.isAnnotationPresent( this.annotation )) {
                    putBeansToProcess( beanName, clazz );
                }
            }
            if (containsField( proxyMode )) {
                //变量上的处理
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent( this.annotation )) {
                        putBeansToProcess( beanName, clazz );
                    }
                }
            }
            if (containsMethod( proxyMode )) {
                //方法上的处理
                for (Method method : clazz.getMethods()) {
                    if (method.isAnnotationPresent( this.annotation )) {
                        putBeansToProcess( beanName, clazz );
                    }
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return bean;

    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (beansToProcess.containsKey( beanName )) {
            Object target = getTargetBean( bean );
            for (Class clazz : beansToProcess.get( beanName )) {

                //类的处理
                if (clazz.isAnnotationPresent( this.annotation )) {
                    Annotation annotation = AnnotationUtils.getAnnotation( clazz, this.annotation );
                    postProcessClz( target, annotation );

                }
                for (Method method : clazz.getMethods()) {
                    if (method.isAnnotationPresent( this.annotation )) {
                        Annotation annotation = AnnotationUtils.getAnnotation( method, this.annotation );
                        postProcessMethod( target, method, annotation );
                    }
                }
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent( this.annotation )) {
                        Annotation annotation = AnnotationUtils.getAnnotation( field, this.annotation );
                        postProcessField( target, field, annotation );
                    }
                }
            }

        }
        return bean;

    }

    private Object getTargetBean(Object bean) {
        Object target = bean;
        while (AopUtils.isAopProxy( target )) {
            try {
                target = ((Advised) target).getTargetSource().getTarget();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return target;
    }


    private void putBeansToProcess(String beanName, Class clazz) {
        if (!beansToProcess.containsKey( beanName )) {
            beansToProcess.put( beanName, new ArrayList<Class>() );
        }
        beansToProcess.get( beanName ).add( clazz );
    }

    public static boolean containsClass(ProxyMode proxyMode) {
        if (proxyMode == ALL || proxyMode == CLASS_ANNOTATION
                || proxyMode == CLASS_FEILD_ANNOTATION || proxyMode == CLASS_METHOD_ANNOTATION) {
            return true;
        }
        return false;
    }

    public static boolean containsField(ProxyMode proxyMode) {
        if (proxyMode == ALL || proxyMode == FIELD_ANNOTATION
                || proxyMode == CLASS_FEILD_ANNOTATION || proxyMode == FIELD_METHOD_ANNOTATION) {
            return true;
        }
        return false;
    }

    public static boolean containsMethod(ProxyMode proxyMode) {
        if (proxyMode == ALL || proxyMode == METHOD_ANNOTATION
                || proxyMode == CLASS_METHOD_ANNOTATION || proxyMode == FIELD_METHOD_ANNOTATION) {
            return true;
        }
        return false;
    }

    public void setProxyMode(ProxyMode proxyMode) {
        this.proxyMode = proxyMode;
    }
}
