package pers.avc.wechat.core.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 *  微信消息处理器
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WeChatMessageProcessor {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
