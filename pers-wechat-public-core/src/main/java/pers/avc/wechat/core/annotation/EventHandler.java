package pers.avc.wechat.core.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EventHandler {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
