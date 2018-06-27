package io.github.forezp.examples.aop;

import io.github.forezp.scrorpio.proxy.AbstractFieldBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-27
 **/
@Component
public class FieldBeanPostProcessor extends AbstractFieldBeanPostProcessor {

    @Override
    public Object getFieldTarget(Annotation annotation) {
        if (annotation instanceof Reference) {
            Reference reference = (Reference) annotation;
            Referentity referentity = new Referentity();
            referentity.setName( reference.name() );
            referentity.setUrl( reference.url() );
            return referentity;
        }
        return null;
    }

    @Override
    public Class<? extends Annotation> getAnnoatation() {
        return Reference.class;
    }

}
