package io.github.forezp.scrorpio.proxy;

import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Email miles02@163.com
 * 给变量设置一个值
 *
 * @author fangzhipeng
 * create 2018-06-27
 **/
public abstract class AbstractFieldBeanPostProcessor extends AbstractBeanPostProcessor {

    public abstract Object getFieldTarget(Annotation annotation);

    @Override
    public Object postProcessClz(Object target, Annotation annotation) {
        return null;
    }

    @Override
    public Object postProcessMethod(Object target, Method method, Annotation annotation) {
        return null;
    }

    @Override
    public Object postProcessField(Object target, Field field, Annotation annotation) {
        if (null != annotation) {
            ReflectionUtils.makeAccessible( field );
            ReflectionUtils.setField( field, target, getFieldTarget( annotation ) );
        }
        return null;
    }
}
