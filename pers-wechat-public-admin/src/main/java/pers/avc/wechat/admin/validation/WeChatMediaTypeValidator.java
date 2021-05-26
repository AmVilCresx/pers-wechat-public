package pers.avc.wechat.admin.validation;

import org.springframework.util.StringUtils;
import pers.avc.wechat.admin.contants.MediaType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 微信媒体类型校验器
 *
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class WeChatMediaTypeValidator implements ConstraintValidator<WeChatMediaTypeValidate, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasText(s)) {
            return false;
        }
        MediaType type = MediaType.getTypeByName(s);
        return Objects.nonNull(type);
    }
}
