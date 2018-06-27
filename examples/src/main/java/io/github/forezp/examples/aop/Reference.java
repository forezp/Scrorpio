package io.github.forezp.examples.aop;

import java.lang.annotation.*;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-27
 **/
@Target(ElementType.FIELD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Reference {

    String name() default "";

    String url();
}
