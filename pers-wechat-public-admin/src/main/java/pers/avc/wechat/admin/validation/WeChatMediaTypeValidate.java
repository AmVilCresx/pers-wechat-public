package pers.avc.wechat.admin.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WeChatMediaTypeValidator.class)
public @interface WeChatMediaTypeValidate {

    String message() default "媒体类型错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
